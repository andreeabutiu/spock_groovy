import spock.lang.Shared
import spock.lang.Specification

class FalseNegativeTest extends Specification {

    @Shared
    int counter = 0

    def "counter plus equals can be used"() {
        when:
        counter+=1

        then:
        counter == 1

        when:
        counter++

        then:
        counter == 1

        when:
        counter = counter + 2

        then:
        counter == 4
    }


    def "counter is result of addition"() {
        when:
        counter = counter + 2

        then:
        counter == 4
    }


    def "counter minus equals can be used"() {
        when:
        counter-=1

        then:
        counter == 3
    }

    def "counter minus minus"() {
        when:
        counter--

        then:
        counter == 2
    }

    def "counter is result of subtraction"() {
        when:
        counter = counter - 2

        then:
        counter == 0
    }
}
