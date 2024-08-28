/**
 * Component Gerrit is defined as
 * `<e-gerrit>`
 *
 * Imperatively create component
 * @example
 * let component = new Gerrit();
 *
 * Declaratively create component
 * @example
 * <e-gerrit></e-gerrit>
 *
 * @extends {LitComponent}
 */
import { definition } from '@eui/component';
import { LitComponent, html } from '@eui/lit-component';
import style from './gerrit.css';

/**
 * @property {Boolean} propOne - show active/inactive state.
 * @property {String} propTwo - shows the "Hello World" string.
 */
@definition('e-gerrit', {
    style,
    home: 'gerrit',
    props: {
        commitTableData: {
            attribute: false,
            type: "array",
            default: [{
                "c_hash": "-1",
                "c_author": "-select one-",
                "c_message": "-1",
                "c_date": "-select one-",
                "c_project_name": "-1",
                "c_lines_added": "-select one-",
                "c_lines_deleted": "-select one-"
            }]
        },
    },
})
export default class Gerrit extends LitComponent {
    /**
     * Render the <e-gerrit> component. This function is called each time a
     * prop changes.
     */
    render() {

        const columns = [
            { title: 'Hash', attribute: 'c_hash' },
            { title: 'Author', attribute: 'c_author' },
            { title: 'Message', attribute: 'c_message' },
            { title: 'Date', attribute: 'c_date' },
            { title: 'Project', attribute: 'c_project_name' },
            { title: 'Lines Added', attribute: 'c_lines_added' },
            { title: 'Lines Deleted', attribute: 'c_lines_deleted' }
        ];

        fetch('http://localhost:8085/allCommits')
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
                    commitData['c_project_name'] = t.c_project_name;
                    commitData['c_lines_added'] = t.c_lines_added;
                    commitData['c_lines_deleted'] = t.c_lines_deleted;
                    this.commitTableData.push(commitData);

                }

                this.loaded = true;
                console.log(this.commitTableData);
            });
        return html `
                <div class='commit-metric-table'>
                <h3>Gerrit Metrics :</h3>
                <eui-table-v0 .columns=${columns} .data=${this.commitTableData}></eui-table-v0>
                </div>        
    `;
    }
}

/**
 * Register the component as e-gerrit.
 * Registration can be done at a later time and with a different name
 */
Gerrit.register();