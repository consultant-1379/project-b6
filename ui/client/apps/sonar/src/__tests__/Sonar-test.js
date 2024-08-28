/**
 * Integration tests for <e-sonar>
 */
import { expect } from 'chai';
import Sonar from '../Sonar';
import {
  inShadow,
  injectHTMLElement,
} from '../../../../../test/utils';

describe('Sonar Application Tests', () => {
    let container;
    let inject;
    before(() => {
      container = document.body.appendChild(document.createElement('div'));
      inject = injectHTMLElement.bind(null, container);
      window.EUI = undefined; // stub out the locale
      Sonar.register();
    });

    after(() => {
      document.body.removeChild(container);
    });

    describe('Basic application setup', () => {
      it('should create a new <e-sonar>', async () => {
        const appUnderTest = await inject('<e-sonar></e-sonar>');
        // check shadow DOM
        const headingTag = inShadow(appUnderTest, 'h1');
        expect(headingTag.textContent, '"Your app markup goes here" was not found').to.equal('Your app markup goes here');
      });
    });
});
