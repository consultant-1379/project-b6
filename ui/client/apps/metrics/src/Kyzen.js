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
import style from './kyzen.css';
import '@eui/table';




@definition('e-kyzen', {
    style,
    props: {

        teamNames: { attribute: false, type: 'array', default: [{ "value": "-1", "label": "-select all teams-" }] },
        teamName: { attribute: false, default: 'volt' },
        startDate: { attribute: false, default: '2015-01-01' },
        endDate: { attribute: false, default: Date.now() },
        day: { attribute: false, default: Date.now() },
        teamMembers: { attribute: false, default: [] },
        loaded: { attribute: false, default: false },
        commitsPerDay: { attribute: false, type: 'number', default: 0 },
        linesAdded: { attribute: false, type: 'number', default: 0 },
        linesDeleted: { attribute: false, type: 'number', default: 0 },
        activeDays: { attribute: false, type: 'number', default: 0 },
        commitTableData: { attribute: false, default: [] }
    },
})
export default class Kyzen extends App {

    render() {
        const { EUI } = window;

        const multiPanelTileDOM = document.querySelector('.multi-panel-tile');

        if (multiPanelTileDOM) {
            const multiPanelTile = new MultiPanelTile(multiPanelTileDOM);
            multiPanelTile.init();
        }


        var columns = [
            { title: 'Hash', attribute: 'c_hash' },
            { title: 'Author', attribute: 'c_author' },
            { title: 'Message', attribute: 'c_message' },
            { title: 'Date', attribute: 'c_date' },
            { title: 'Project', attribute: 'c_project_name' },
            { title: 'Lines +', attribute: 'c_lines_added' },
            { title: 'Lines -', attribute: 'c_lines_deleted' }
        ];
        if (!this.loaded) {
            this._init();
            this._updateTeams();
        }

        return html `    
        <eui-layout-v0-tile title="My first" style="padding:0%; background-color:#dcdcdc">
          
            <div slot="content">          
            <eui-layout-v0-tile title="My first" style="background-color:#dcdcdc;">          
                <div slot="content" >
                    <span style="margin-right:80px; color:#1174e6; font-size:16px"><b>Teams:</b></span>                                       
                    <eui-base-v0-dropdown id="teams" data-type="single" @eui-dropdown:change="${(event) => this._selectCommitsByTeamName(event.currentTarget.value)}" label="-Select a Team-" .data=${this.teamNames}></eui-base-v0-dropdown>
                </div>
                <div class="loading large"></div>
            </div>
        </eui-layout-v0-tile>

        <eui-layout-v0-tile title="second" style="background-color:#dcdcdc">            
            <div slot="content">    
                <div class='commit-metric-table'>
                    <h3 style="color=coral">Gerrit Metrics :</h3>
                    <eui-table-v0 .columns=${columns} class="table-row" .data=${this.commitTableData} resizable ></eui-table-v0>                     
                </div>                   
            </div>            
        </eui-layout-v0-tile>

        <eui-layout-v0-tile title="third" style="background-color:#dcdcdc">            
            <div slot="content"> 
                <div style="margin-bottom:30px"><span style="margin-right:10px; color:#1174e6; font-size:14px"><b>[Active Days]</b></span></div>
                <span style="margin-right:10px; color:#1174e6; font-size:12px"><b>Start Week:</b></span>  
                <eui-base-v0-datepicker date='2015-01-01' @eui-datepicker:change="${(event) => {this.startDate= event.detail.date;this._updateActiveDays()}}"></eui-base-v0-datepicker> 
                <span style="margin-right:10px; color:#1174e6; font-size:12px"><b>End Week:</b></span>  
                <eui-base-v0-datepicker @eui-datepicker:change="${(event) => {this.endDate= event.detail.date;this._updateActiveDays()}}"></eui-base-v0-datepicker> 
                <span style="margin-right:10px; color:#1174e6; font-size:12px"><b>No. of Active Days:</b></span> 
                <eui-base-v0-text-field disabled=true value = ${this.activeDays}></eui-base-v0-text-field>
            </div>
        </eui-layout-v0-tile>

        <eui-layout-v0-tile title="fourth" style="background-color:#dcdcdc; padding-bottom:50px">            
            <div slot="content">    
                <div style="margin-bottom:30px; "><span style="margin-right:10px; color:#1174e6; font-size:14px"><b>[Commits Per Day]</b></span></div>
                    <span style="margin-right:47px; color:#1174e6; font-size:12px"><b>Day:</b></span>  
                    <eui-base-v0-datepicker date='2015-01-01' @eui-datepicker:change="${(event) => {this.day= event.detail.date;this._updateCommitsPerDay();this._updateLinesAdded();this._updateLinesDeleted()}}"></eui-base-v0-datepicker> 
                    <span style="margin-right:10px; color:#1174e6; font-size:12px"><b>No. of Commits:</b></span> 
                    <eui-base-v0-text-field disabled=true value = ${this.commitsPerDay}></eui-base-v0-text-field>
                    <span style="margin-right:10px; color:#1174e6; font-size:12px"><b>Lines(+) :</b></span> 
                    <eui-base-v0-text-field disabled=true value = ${this.linesAdded}></eui-base-v0-text-field>
                    <span style="margin-right:10px; color:#1174e6; font-size:12px"><b>Lines(-) :</b></span> 
                    <eui-base-v0-text-field disabled=true value = ${this.linesDeleted}></eui-base-v0-text-field>
                </div>
            </div>
        </eui-layout-v0-tile>
    `;
    }

    _init() {
        fetch('http://localhost:8085/allCommits')
            .then(res => res.text())
            .then(text => {
                this.response = text;
                let commits = JSON.parse(text);
                var commitTable = []
                for (var t of Object.values(commits)) {
                    var commitData = {};
                    commitData['c_hash'] = t.c_hash;
                    commitData['c_author'] = t.c_author;
                    commitData['c_message'] = t.c_message;
                    commitData['c_date'] = t.c_date;
                    commitData['c_project_name'] = t.c_project_name;
                    commitData['c_lines_added'] = t.c_lines_added;
                    commitData['c_lines_deleted'] = t.c_lines_deleted;
                    commitTable.push(commitData);
                }

                this.loaded = true;
                this.commitTableData = commitTable;
                console.log(this.commitTableData);
            });
    }

    _selectCommitsByTeamName(teamName) {
        if (teamName[0].label == '-select all teams-') {
            this._init();
        } else {
            var pathVariable = teamName[0].label;
            pathVariable = encodeURIComponent(pathVariable)
            this.teamName = pathVariable;
            var commitTable = [];
            var url = `http://localhost:8085/allCommits/` + pathVariable + '/';



            console.log(url)
            fetch(url)
                .then(res => res.text())
                .then(text => {
                    this.response = text;
                    let commits = JSON.parse(text);

                    for (var t of Object.values(commits)) {
                        let commitData = {};
                        commitData['c_hash'] = t.c_hash;
                        commitData['c_author'] = t.c_author;
                        commitData['c_message'] = t.c_message;
                        commitData['c_date'] = t.c_date;
                        console.log(t.c_date)
                        commitData['c_project_name'] = t.c_project_name;
                        commitData['c_lines_added'] = t.c_lines_added;
                        commitData['c_lines_deleted'] = t.c_lines_deleted;
                        commitTable.push(commitData);
                    }

                    this.loaded = true;

                    this.commitTableData = commitTable;
                    console.log(this.commitTableData);
                });
        }
    }

    _updateTeams() {
        fetch('http://localhost:8080/team')
            .then(res => res.text())
            .then(text => {

                this.response = text;
                let team = JSON.parse(text);
                for (var t of Object.values(team)) {
                    console.log(t.name);
                    let teamData = {};
                    teamData['value'] = t.teamId;
                    teamData['label'] = t.name;


                    this.teamNames.push(teamData);
                }
                console.log(this.teamNames)
            });
    }


    _updateActiveDays() {
        var url = `http://localhost:8085/commits/activeDaysInAWeek/?team=${this.teamName}&start=${this.startDate}&end=${this.endDate}`;
        console.log(url)
        fetch(url)
            .then(res => res.text())
            .then(text => {
                this.response = text;
                let commits = JSON.parse(text);

                this.loaded = true;
                console.log(commits);

                this.activeDays = commits;
            });
    }

    _updateCommitsPerDay() {
        var url = `http://localhost:8085/commits/commitsPerDay/?team=${this.teamName}&day=${this.day}`;
        console.log(url)
        fetch(url)
            .then(res => res.text())
            .then(text => {
                if (text != '') {
                    this.response = text;
                    let commits = JSON.parse(text);

                    this.loaded = true;
                    this.commitsPerDay = commits;
                } else {
                    this.commitsPerDay = 0;
                }
            });
    }

    _updateLinesAdded() {
        var url = `http://localhost:8085/commits/commitLinesAdded/?team=${this.teamName}&day=${this.day}`;
        console.log(url)
        fetch(url)
            .then(res => res.text())
            .then(text => {
                if (text != '') {
                    this.response = text;
                    let lines = JSON.parse(text);
                    console.log(lines);
                    this.linesAdded = lines;
                } else {
                    this.linesAdded = 0;
                }
            });
    }

    _updateLinesDeleted() {
        var url = `http://localhost:8085/commits/commitLinesDeleted/?team=${this.teamName}&day=${this.day}`;
        console.log(url)
        fetch(url)
            .then(res => res.text())
            .then(text => {
                if (text != '') {
                    this.response = text;
                    let lines = JSON.parse(text);

                    console.log(lines);
                    this.linesDeleted = lines;
                } else {
                    this.linesDeleted = 0;
                }
            });
    }

}

/**
 * Register the component as e-app-1.
 * Registration can be done at a later time and with a different name
 * Uncomment the below line to register the App if used outside the container
 */
// App1.register();