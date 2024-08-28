/**
 * App1 is defined as
 * `<e-app-1>`
 *
 * Imperatively create application
 * @example
 * let app = new App1();
 *
 * Declaratively create application
 * @example
 * <e-app-1></e-app-1>
 *
 * @extends {App}
 */
import { definition } from '@eui/component';
import { App, html } from '@eui/app';
import style from './app1.css';
import '@eui/table';

@definition('e-app-1', {
    style,
    props: {
        response: { attribute: false, default: { 'ck': 'lmao' } },
        teamName: { attribute: false, default: 'Team' },
        teamMembers: { attribute: false, default: [] },
        loaded: { attribute: false, default: false },
        tableData: { attribute: false, default: [] },
        jenkinsData: { attribute: false, default: [] },
        sonarData: { attribute: false, default: [] }
    },
})
export default class App1 extends App {
    /**
     * Uncomment this block to add initialization code
     * constructor() {
     *   super();
     *   // initialize
     * }
     */

    /**
     * Render the <e-app-1> app. This function is called each time a
     * prop changes.
     */
    render() {
        const { EUI } = window;

        const columns = [
            { title: 'Metric Name', attribute: 'name' },
            { title: 'Metric Value ', attribute: 'value' }
        ];



        if (!this.loaded) {

            fetch('/metrics')
                .then(res => res.text())
                .then(text => {

                    this.response = text;
                    let team = JSON.parse(text);
                    this.teamName = team['name'];

                    this.teamMembers = team['members'].map(member => {
                        let mem = {};
                        mem['label'] = member['name'];
                        return mem;
                    });


                    let jenkinsMetric = team['jenkinsMetrics'][0];
                    for (var key of Object.keys(jenkinsMetric)) {
                        if (key != 'metricId')
                            this.jenkinsData.push({ name: key, value: jenkinsMetric[key] });

                    }

                    let sonarMetric = team['sonarMetrics'][0];
                    for (var key of Object.keys(sonarMetric)) {
                        if (key != 'metricId')
                            this.sonarData.push({ name: key, value: sonarMetric[key] });

                    }

                    let commitMetric = team['commitMetrics'][0];
                    for (var key of Object.keys(commitMetric)) {
                        if (key != 'metricId')
                            this.tableData.push({ name: key, value: commitMetric[key] });

                    }



                    //console.log(this.tableData);
                    this.loaded = true;
                    //console.log(team['name']);
                });

        }


        return html `
    <h1>${this.teamName}</h1>
    
    
    <div class='member-tree'>
    <span><h3>Members :</h3></span>
    <eui-base-v0-tree .data=${this.teamMembers}></eui-base-v0-tree>
    </div>
    <div class='commit-metric-table'>
    <span><h3>Gerrit Metrics :</h3></span>
    <eui-table-v0 .columns=${columns} .data=${this.tableData}></eui-table-v0></div>
    <div class='jenkins-metric-table'>
    <h3>Jenkins Mertics :</h3>
    <eui-table-v0 .columns=${columns} .data=${this.jenkinsData}></eui-table-v0></div>
    </div>
    <div class='sonar-metric-table'>
    <h3>SonarQube Mertics :</h3>
    <eui-table-v0 .columns=${columns} .data=${this.sonarData}></eui-table-v0></div>
    </div>
    `;

        //<center>
        //<div class= 'main'>${this.response}</div>
    }
}

/**
 * Register the component as e-app-1.
 * Registration can be done at a later time and with a different name
 * Uncomment the below line to register the App if used outside the container
 */
// App1.register();