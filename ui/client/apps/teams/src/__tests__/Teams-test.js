/**
 * Integration tests for <e-teams>
 */
import { expect } from 'chai';
import Teams from '../Teams';
import {
  inShadow,
  injectHTMLElement,
} from '../../../../../test/utils';

describe('Teams Application Tests', () => {
    let container;
    let inject;
    before(() => {
      container = document.body.appendChild(document.createElement('div'));
      inject = injectHTMLElement.bind(null, container);
      window.EUI = undefined; // stub out the locale
      Teams.register();
    });

    after(() => {
      document.body.removeChild(container);
    });

    describe('Basic application setup', () => {
      it('should create a new <e-teams>', async () => {
        const appUnderTest = await inject('<e-teams></e-teams>');
        // check shadow DOM
        const headingTag = inShadow(appUnderTest, 'h1');
        expect(headingTag.textContent, '"Your app markup goes here" was not found').to.equal('Your app markup goes here');
      });
    });
});
