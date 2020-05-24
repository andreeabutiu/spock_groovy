import spock.lang.*

class First extends Specification{

    def "one plus one should equal two"() {
        expect: '1+1 = 2'
        1 + 1 == 2
    }

    def "two plus two should equal four"() {
        given: '2 numbers'
        int left = 2
        int right = 2

        when: 'add the numbers'
        int result = left + right

        then: 'result is 4'
        result == 4
    }

    def "Should be able to remove from list"() {
        given:
        def list = [1, 2, 3, 4]

        when:
        list.remove(0)

        then:
        list == [2, 3, 4]
    }

    def "numbers to the power of two"(int a, int b, int c) {
        expect:
        Math.pow(a, b) == c

        where:
        a | b | c
        1 | 2 | 1
        2 | 2 | 4
        3 | 2 | 9
    }

}



