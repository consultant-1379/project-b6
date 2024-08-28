/**
 * Component Sonar is defined as
 * `<e-sonar>`
 *
 * Imperatively create component
 * @example
 * let component = new Sonar();
 *
 * Declaratively create component
 * @example
 * <e-sonar></e-sonar>
 *
 * @extends {LitComponent}
 */
import { definition } from '@eui/component';
import { LitComponent, html } from '@eui/lit-component';
import style from './sonar.css';

/**
 * @property {Boolean} propOne - show active/inactive state.
 * @property {String} propTwo - shows the "Hello World" string.
 */
@definition('e-sonar', {
    style,
    home: 'sonar',
    props: {
        propOne: { attribute: true, type: Boolean },
        propTwo: { attribute: true, type: String, default: 'Hello World' },
    },
})
export default class Sonar extends LitComponent {
    /**
     * Render the <e-sonar> component. This function is called each time a
     * prop changes.
     */
    render() {
        return html `<h1>Sonar</h1>
    `;
    }
}

/**
 * Register the component as e-sonar.
 * Registration can be done at a later time and with a different name
 */
Sonar.register();