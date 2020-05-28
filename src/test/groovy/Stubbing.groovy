import spock.lang.Specification


//define what should happen when a particular method of stubbed class is invoked
class Stubbing extends Specification {

    def "Stubbing1"() {
        given:
        List list = Stub(List)
        list.size() >> 3
        //list.size() >> { println "Size method has been invoked" }
        //list.size() >> { throw new IllegalStateException() }
        expect:
        // let's see if this works
        list.size() == 3
    }

    def "should return 2 for method parameter equal to 2"() {
        given:
        List list = Stub()
        list.get(0) >> 0
        list.get(1) >> { throw new IllegalArgumentException() }
        list.get(2) >> 2
        expect:
        list.get(2) == 2
        list.get(0) == 0
        //list.get(1) == 2
        //list.size() == 3
    }

    def "should return 2 for method parameter equal to 2 - part2"() {
        given:
        List list = Stub()
        list.add(_ as Integer) >> { throw new IllegalArgumentException() }

        when:
        list.add(10)

        then:
           thrown(IllegalArgumentException)

    }

}
