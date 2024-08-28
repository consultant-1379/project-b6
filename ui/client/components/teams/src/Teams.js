/**
 * Component Teams is defined as
 * `<e-teams>`
 *
 * Imperatively create component
 * @example
 * let component = new Teams();
 *
 * Declaratively create component
 * @example
 * <e-teams></e-teams>
 *
 * @extends {LitComponent}
 */
import { definition } from '@eui/component';
import { LitComponent, html } from '@eui/lit-component';
import style from './teams.css';


/**
 * @property {Boolean} propOne - show active/inactive state.
 * @property {String} propTwo - shows the "Hello World" string.
 */
@definition('e-teams', {
    style,
    home: 'teams',
    props: {
        teamNames: { attribute: false, type: 'array', default: [{ "value": "-1", "label": "-select one-" }] },
        loaded: { attribute: false, default: false },
    },
})
export default class Teams extends LitComponent {
    /**
     * Render the <e-teams> component. This function is called each time a
     * prop changes.
     */

    render() {
        if (!this.loaded) {
            fetch('http://localhost:8080/teams')
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



            return html `
        <eui-layout-v0-tile title="My first" style="background-color:#dcdcdc">
          
          <div slot="content" >
            <span style="margin-right:80px; color:#1174e6; font-size:16px"><b>Teams:</b></span>
            <eui-base-v0-combo-box id = "teams" label="label" @click=${(event) =>  this._update(event.currentTarget.value)} placeholder="-select a team-" data-type="single" .data=${this.teamNames}>
            </eui-base-v0-combo-box>
          </div>
        </eui-layout-v0-tile>

        <div>
        `;

        }
    }

    _update(value) {
        _selectCommitsByTeamName(value);
    }

}

/**
 * Register the component as e-teams.
 * Registration can be done at a later time and with a different name
 */
Teams.register();