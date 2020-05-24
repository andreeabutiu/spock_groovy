import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import spock.lang.Shared
import spock.lang.Specification

class APIJokes extends Specification {

    @Shared
    def client = new RESTClient('http://api.icndb.com/')

    def 'should return 200 code'() {
        when: 'get random jokes'
        def response = client.get(path: '/jokes/random')

        then: 'server returns 200 code (ok)'
        //assert response.status == 200: 'response code should be 200'  TODO
    }

    def 'should return 200 code also with params'() {

        given: 'a first and a last name parameters'
        def param = [firstName: "1stName", lastName: "lastName"]

        when: 'get random jokes with params'
        def response = client.get(path: '/jokes/random', param: param)

        then: 'server returns 200 code (ok)'
        //assert response.status == 200: 'response code should be 200'  TODO
    }
}

class APIRestullBooker extends Specification {

    @Shared
    def client = new RESTClient('https://restful-booker.herokuapp.com')

    def 'ping'() {
        when: 'get random jokes'
        HttpResponseDecorator response = client.get(path: '/ping')

        then: 'server returns 201 code (created)'
        assert response.responseBase.statusLine.toString().contains('203')
    }

    def 'should return 200 code and Bad Credentials'() {

        when: 'send a POST auth with error'
        def response = client.get(path: '/auth')

        then: 'server returns 200 code (ok)'
        HttpResponseException e = thrown(HttpResponseException)
        assert e.response.status == 404: 'response code should be 404'

    }
}
