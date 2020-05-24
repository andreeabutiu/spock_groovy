import spock.lang.Specification

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
    }


    def 'Test service with mock DAO2'() {
        given:
        // Creating a mock implementation
        def daoMock = Mock(UserDao)
        // Setting return values
        daoMock.delete(_) >> true >> false
        // Or with a list: daoMock.delete(_) >> [true, false]

        expect:
        daoMock.delete(1) == true
        daoMock.delete(1) == false
        daoMock.delete(2) == false
    }
}
