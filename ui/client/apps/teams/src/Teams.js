/**
 * Teams is defined as
 * `<e-teams>`
 *
 * Imperatively create application
 * @example
 * let app = new Teams();
 *
 * Declaratively create application
 * @example
 * <e-teams></e-teams>
 *
 * @extends {App}
 */
import { definition } from '@eui/component';
import { App, html } from '@eui/app';
import style from './teams.css';

@definition('e-teams', {
    style,
    props: {
        teamNames: { attribute: false, type: 'array', default: [] },
        loaded: { attribute: false, default: false },
        imageName: { attribute: false, default: 'volt.jpg' },
    },
})
export default class Teams extends App {
    /**
     * Uncomment this block to add initialization code
     * constructor() {
     *   super();
     *   // initialize
     * }
     */

    /**
     * Render the <e-teams> app. This function is called each time a
     * prop changes.
     */
    render() {

        if (!this.loaded) {
            this._updateTeams();
        }

        return html `
        <eui-layout-v0-tile title="second">
          
            <div slot="content" style="background-color:#dcdcdc;padding-top:20px;padding-bottom:20px">      
                    
                    <span style="margin-right:10px;margin-left:10px; color:#1174e6; font-size:14px"><b>Teams:</b></span>                                       
                    <eui-base-v0-dropdown id="teams" data-type="single" @eui-dropdown:change="${(event) => this._updateImage(event.currentTarget.value)}" label="-List of Teams-" .data=${this.teamNames}></eui-base-v0-dropdown>
                    <img id = logo style="margin-top:40px" src="../../images/${this.imageName}">
                <div class="loading large"></div>
            </div>
        </eui-layout-v0-tile>
        <eui-layout-v0-tile title="first" >            
            <div slot="content" style="background-color:#dcdcdc;padding-top:20px;padding-bottom:20px;padding-left:10px">    
                    <eui-base-v0-button  style ="margin-right:10px" @click="${(event) =>  this._addTeam()}">Add</eui-base-v0-button>
                    
                    <eui-base-v0-text-field placeholder="- enter team name -"></eui-base-v0-text-field>
            </div>
        </eui-layout-v0-tile>
        `;
    }

    _addTeam() {
        console.log("add team")
    }

    _updateTeams() {
        fetch('http://localhost:8080/teams')
            .then(res => res.text())
            .then(text => {

                this.response = text;
                let team = JSON.parse(text);
                var tempTeamData = [];
                for (var t of Object.values(team)) {
                    console.log(t.name);
                    let teamData = {};
                    teamData['value'] = t.teamId;
                    teamData['label'] = t.name;


                    tempTeamData.push(teamData);
                }
                this.teamNames = tempTeamData;
                console.log(this.teamNames)
            });
        this.loaded = true;
    }

    _updateImage(imageName) {
        imageName = imageName[0].label;
        console.log(imageName);
        if (imageName == 'Volt') {
            this.imageName = 'volt.jpg';

        } else if (imageName == 'Looney Tunes') {
            this.imageName = 'looney_tunes.png';

        } else if (imageName == 'Indigo') {
            this.imageName = 'indigo.png';
        }
    }
}

/**
 * Register the component as e-teams.
 * Registration can be done at a later time and with a different name
 * Uncomment the below line to register the App if used outside the container
 */
Teams.register();