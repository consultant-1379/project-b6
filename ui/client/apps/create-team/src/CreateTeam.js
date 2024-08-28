/**
 * CreateTeam is defined as
 * `<e-create-team>`
 *
 * Imperatively create application
 * @example
 * let app = new CreateTeam();
 *
 * Declaratively create application
 * @example
 * <e-create-team></e-create-team>
 *
 * @extends {App}
 */
import { definition } from '@eui/component';
import { App, html } from '@eui/app';
import { ComboBox } from '@eui/base';
import style from './createTeam.css';


@definition('e-create-team', {
  style,
  props: {
    response: { attribute: false },
    loaded : {attribute : false, default: false},
    freeMembers : {attribute: false, default: [{'memberId': 0, 'name': 'unset'}]}

  },
})
export default class CreateTeam extends App {
  /**
   * Uncomment this block to add initialization code
   * constructor() {
   *   super();
   *   // initialize
   * }
   */

  /**
  * Render the <e-create-team> app. This function is called each time a
  * prop changes.
  */

//  componentWillRender(prev){
  


//  }

    _doPostTeam(){
        let postData = {};
        



    }

  render() {
    const { EUI } = window;

    
    if (!this.loaded){
      fetch('/freemembers')
  .then(res => res.text())
  .then(text => {
    let members = JSON.parse(text);
    this.freeMembers =  members.map(member => {
      let data = {}
      data['value'] = member['memberId'];
      data['label'] = member['name'];
      data['selected'] = false;
      return data;
    })
    this.loaded = true;
  });
    }
      


    return html`
    
    <h1> Create New Team</h1>
    <div class='my-text-box'>
    <p>Team Name:</p>
    <eui-base-v0-text-field></eui-base-v0-text-field>
    </div>
    <div class='my-combo-box'>
    <p>Team Members:</p>
    <eui-base-v0-combo-box label="label" data-type="multi" .data=${this.freeMembers}>
    </div>
    <div class='my-repo-box'>
    <p>Team Repositories:</p>
    <eui-base-v0-textarea></eui-base-v0-textarea>
    </div>

  
</eui-base-v0-combo-box>
​​
<div>
<eui-base-v0-button  @click="${(event) => this._doPostTeam()}">
Submit</eui-base-v0-button></div>`;
  }
}

/**
 * Register the component as e-create-team.
 * Registration can be done at a later time and with a different name
 * Uncomment the below line to register the App if used outside the container
 */
// CreateTeam.register();
