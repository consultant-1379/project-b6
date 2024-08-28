/**
 * Sonar is defined as
 * `<e-sonar>`
 *
 * Imperatively create application
 * @example
 * let app = new Sonar();
 *
 * Declaratively create application
 * @example
 * <e-sonar></e-sonar>
 *
 * @extends {App}
 */
import { definition } from '@eui/component';
import { App, html } from '@eui/app';
import style from './sonar.css';

@definition('e-sonar', {
    style,
    props: {
        response: { attribute: false },
        projectNames: { attribute: false, type: 'array', default: [{ "value": "-1", "label": "-select all projects-" }] },
        sonarTableData: { attribute: false, default: [] },
        loaded: { attribute: false, default: false }
    },
})
export default class Sonar extends App {

    render() {
        const { EUI } = window;

        const multiPanelTileDOM = document.querySelector('.multi-panel-tile');

        if (multiPanelTileDOM) {
            const multiPanelTile = new MultiPanelTile(multiPanelTileDOM);
            multiPanelTile.init();
        }

        var columns = [
            { title: 'Security Rating', attribute: 'security_rating' },
            { title: 'Code Smells', attribute: 'codeSmells' },
            { title: 'Bugs', attribute: 'bugs' },
            { title: 'Duplications', attribute: 'duplications' },
            { title: 'Project', attribute: 'project_name' },
            { title: 'Metrics', attribute: 'metrics' },
            { title: 'Coverage', attribute: 'coverage' }
        ];
        if (!this.loaded) {
            this._updateProjects();
            this._init();
        }

        return html `    
        <eui-layout-v0-tile title="My first" style="padding:0%; background-color:#dcdcdc">
          
            <div slot="content">          
            <eui-layout-v0-tile title="My first" style="background-color:#dcdcdc;">          
                <div slot="content" >
                    <span style="margin-right:80px; color:#1174e6; font-size:16px"><b>Projects:</b></span>                                       
                    <eui-base-v0-dropdown id="teams" data-type="single" @eui-dropdown:change="${(event) => this._selectProjectByName(event.currentTarget.value)}" label="-Select a Project-" .data=${this.projectNames}></eui-base-v0-dropdown>
                </div>
                <div class="loading large"></div>
            </div>
        </eui-layout-v0-tile>

        <eui-layout-v0-tile title="second" style="background-color:#dcdcdc">            
            <div slot="content">    
                <div class='commit-metric-table'>
                    <h3 style="color=coral">Sonar Metrics :</h3>
                    <eui-table-v0 .columns=${columns} class="table-row" .data=${this.sonarTableData} resizable ></eui-table-v0>                     
                </div>                   
            </div>            
        </eui-layout-v0-tile>
    `;
    }

    _selectProjectByName(projectName) {
        if (projectName[0].label == '-select all projects-') {
            this._init();
        } else {
            var pathVariable = projectName[0].label;
            var sonarTable = [];
            var url = `http://localhost:8081/sonarqubemetrics/` + pathVariable;



            console.log(url)
            fetch(url)
                .then(res => res.text())
                .then(text => {
                    this.response = text;
                    let sonar = JSON.parse(text);
                    console.log(sonar);
                    let sonarData = {};
                    sonarData['security_rating'] = sonar.security_rating;
                    sonarData['codeSmells'] = sonar.codeSmells;
                    sonarData['bugs'] = sonar.bugs;
                    sonarData['duplications'] = sonar.duplications;
                    sonarData['project_name'] = sonar.project_name;
                    sonarData['metrics'] = sonar.metrics;
                    sonarData['coverage'] = sonar.coverage;
                    sonarTable.push(sonarData);
                    this.loaded = true;
                    this.sonarTableData = sonarTable;
                    console.log(this.sonarTableData);
                });
        }
    }
    _updateProjects() {
        fetch('http://localhost:8081/allProjects')
            .then(res => res.text())
            .then(text => {

                this.response = text;
                let projects = JSON.parse(text);
                console.log(projects);
                var count = 0;
                for (var p of Object.values(projects)) {
                    count++;
                    let projectData = {};
                    projectData['value'] = count;
                    projectData['label'] = p;
                    console.log(p)

                    this.projectNames.push(projectData);
                }
                this.loaded = true;
            });
    }

    _init() {
        fetch('http://localhost:8081/sonarqubemetrics')
            .then(res => res.text())
            .then(text => {
                this.response = text;
                let sonar = JSON.parse(text);
                var sonarTable = []
                for (var t of Object.values(sonar)) {
                    let sonarData = {};
                    sonarData['security_rating'] = t.security_rating;
                    sonarData['codeSmells'] = t.codeSmells;
                    sonarData['bugs'] = t.bugs;
                    sonarData['duplications'] = t.duplications;
                    sonarData['project_name'] = t.project_name;
                    sonarData['metrics'] = t.metrics;
                    sonarData['coverage'] = t.coverage;
                    sonarTable.push(sonarData);
                }

                this.loaded = true;
                this.sonarTableData = sonarTable;
                console.log(this.sonarTableData);
            });
    }
}

/**
 * Register the component as e-sonar.
 * Registration can be done at a later time and with a different name
 * Uncomment the below line to register the App if used outside the container
 */
// Sonar.register();