import spock.lang.Specification


//what has happened with the object during the execution of code inside
class MockTest extends Specification {
    def 'Test service with mock DAO1'() {
        given: "Creating a mock implementation"
        // Creating a mock implementation
        def daoMock = Mock(UserDao)

        // In Groovy, this is one way to call a setter
        def service = new UserService(dao: daoMock)

        // For any string passed as parameter return a dummy user from Mock
        daoMock.get(_ as String) >> {
            String email -> new User(email: email)
        }

        when:
        def user = new User()
        user.email = "steve@example.com"

        then:
        service.registerUser(user) == false
        0*service.setDao(daoMock)
    }


    def 'Test service with mock DAO2'() {
        given:
        // Creating a mock implementation
        def daoMock = Mock(UserDao)
        // Setting return values
        daoMock.delete(_) >> true >> false >> true

        expect:
        daoMock.delete(1) == true
        daoMock.delete(2) == false
        daoMock.delete(3) == true

    }
}



class MockedLinkedList extends Specification {

    def "test mocking"()
    {
        setup:
        // In this example mock the behaviour of LinkedList.
        // First step is to get a reference to a mocked LinkedList, by calling Mock(Class).
        def mockedList = Mock(LinkedList)
                {
                    // Tell the mock that if at a later stage mockedList.get(0) is called,
                    // the real implementation should NOT called,
                    // but instead the value "first" should be returned
                    get(0) >> "first"

                    // Tell mock that if at a later stage mockedList.get(1) is called,
                    // the real implementation should NOT called,
                    // but instead a RuntimeException should be thrown
                    get(1) >> {throw new RuntimeException()}
                }

        // Now if you call mockedList.get(0), you will get the value "first"
        when:
        def first = mockedList.get(0)
        then:
        first == "first"

        //If you call mockedList.get(1), exception will be thrown
        when:
        mockedList.get(1)
        then:
        thrown(RuntimeException)

        // Call the method mockedList.get(999), which has not been recorded,
        // and therefore mock's default value is returned
        when:
        def defaultValue = mockedList.get(999)
        then:
        defaultValue == null
    }

    def "verify mock"()
    {
        setup:
        // Create a mock of List interface
        def mockedList = Mock(List)

        when:
        mockedList.add("one")
        mockedList.clear()
        mockedList.add(_)

        then:
        // Verify that add and clear methods from List are called
        1 * mockedList.add("one")
        1 * mockedList.clear()
        1 * mockedList.add(_)
    }

}
