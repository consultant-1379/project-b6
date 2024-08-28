/**
 * Integration tests for <e-create-team>
 */
import { expect } from 'chai';
import CreateTeam from '../CreateTeam';
import {
  inShadow,
  injectHTMLElement,
} from '../../../../../test/utils';

describe('CreateTeam Application Tests', () => {
    let container;
    let inject;
    before(() => {
      container = document.body.appendChild(document.createElement('div'));
      inject = injectHTMLElement.bind(null, container);
      window.EUI = undefined; // stub out the locale
      CreateTeam.register();
    });

    after(() => {
      document.body.removeChild(container);
    });

    describe('Basic application setup', () => {
      it('should create a new <e-create-team>', async () => {
        const appUnderTest = await inject('<e-create-team></e-create-team>');
        // check shadow DOM
        const headingTag = inShadow(appUnderTest, 'h1');
        expect(headingTag.textContent, '"Your app markup goes here" was not found').to.equal('Your app markup goes here');
      });
    });
});
