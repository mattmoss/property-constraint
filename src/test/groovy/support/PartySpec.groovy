package support

import grails.testing.gorm.DomainUnitTest
import grails.validation.ValidationException
import spock.lang.Specification

class PartySpec extends Specification implements DomainUnitTest<Party> {

    void 'test that Party will fail validation if name is null'() {
        given:
        Party party = new Party()

        when:
        party.save(failOnError: true)

        then:
        ValidationException ex = thrown()
        ex.message.contains 'support.Party.name.nullable.error'
    }

    void 'test that Party will fail validation if name is blank'() {
        given:
        Party party = new Party(name: '  ')

        when:
        party.save(failOnError: true)

        then:
        ValidationException ex = thrown()
        ex.message.contains 'support.Party.name.blank.error'
    }

    void 'test that Party will fail validation if name is too long'() {
        given:
        Party party = new Party(name: 'this name is most assuredly longer than the maximum size permitted for the property')

        when:
        party.save(failOnError: true)

        then:
        ValidationException ex = thrown()
        ex.message.contains 'support.Party.name.maxSize.error'
    }

    void 'test that Party will pass validation if name is good'() {
        given:
        Party party = new Party(name: 'good name')

        when:
        party.save(failOnError: true)

        then:
        noExceptionThrown()
        party.name == 'good name'
    }

    void 'test that Party will pass validation if name (via info) is good'() {
        given:
        Party party = new Party()

        and:
        Information info = new Information()
        info.addToParties(party)

        when:
        party.save(failOnError: true)

        then:
        noExceptionThrown()
        party.name == 'Bob'
    }


    void 'test that default Information with one default Party will validate'() {
        given:
        Party party = new Party()

        and:
        Information info = new Information()
        info.addToParties(party)

        when:
        info.save(failOnError: true)

        then:
        noExceptionThrown()
    }

}
