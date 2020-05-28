import groovyx.net.http.ContentType
import groovyx.net.http.HttpResponseDecorator
import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import net.sf.json.JSON
import spock.lang.Shared
import spock.lang.Specification


class APIJokes extends Specification {

    @Shared
    def client = new RESTClient('http://api.icndb.com/')

    def 'should return 200 code'() {
        when: 'get random jokes'
        println 'get random jokes'
        client.contentType = ContentType.JSON
        HttpResponseDecorator response = client.get(path: '/jokes/random')

        then: 'server returns 200 code (ok)'
        println 'server returns 200 code (ok)'
        assert response.status == 200
        println response.getData()

    }


    def 'should return 200 code also with params'() {

        given: 'a first and a last name parameters'
        def param = [firstName: "1stName", lastName: "lastName"]

        when: 'get random jokes with params'
        HttpResponseDecorator response = client.get(path: '/jokes/random', params: param)

        then: 'server returns 200 code (ok)'
        assert response.status == 200: 'response code should be 200'
        println response.getData()
    }
}


class API_APP extends Specification {

    @Shared
    def client = new RESTClient('https://floating-savannah-86891.herokuapp.com')
    def name = "testare25_ligaAcLabs"
    def email = "teatare25@ligaAcLabs"
    def password = "password25LIgaAcLabs"
    def id


    def 'create user'() {
        when: 'send a post to create a user'

        client.contentType = ContentType.JSON
        HttpResponseDecorator responseCreate
        HttpResponseDecorator responseAuth


        responseCreate = client.post(path: '/api/users',
                    body: [name    : name,
                           email   :  email,
                           password:  password])




        then: 'server returns 200 code (created)'
        assert responseCreate.status == 200

        /*response.getData().each { entry ->
            if (entry.key == "name")
                name = entry.value
            if (entry.key == "email")
                email = entry.value
            if (entry.key == "_id")
                id = entry.value
        }
        println "$name: $email: $id"

        assert name == name
        assert email == email*/

        //given : 'auth token'
        //Map<String, String> headers = new HashMap<String, String>(["content-type": 'application/json; charset=utf-8'])
        //client.setHeaders(headers)
        //client.contentType = ContentType.JSON
        //client.setHeaders(headers)

        //HttpResponseDecorator response
        //response instanceof HTMLDocument
        //response.contentType = ContentType.HTML

        when:  'send auth'
        try {
            responseAuth = client.post(path: '/api/auth',
                    body: [email: email, password: password])



        }
        catch(HttpResponseException e) {
            def r = e.response
            println("Success: $r.success")
            println("Status: $r.status")
            println(e)

        }

        then: 'verify auth'
          assert responseAuth.status == 200

        //id = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZWNlYTZlMjE4ZDBmNDAwMTc1NTQxZWIiLCJpYXQiOjE1OTA2MDkzOTd9.ZgJPc4JoT-hfka_o-58U-KCbonAGpsksj_ObsgaT8uA'


    }

    def 'get_customer_by_id'() {
        when: 'send a get on a specific id'

        client.contentType = ContentType.JSON
        HttpResponseDecorator response


        String test = '/api/customers/'
        test += id

        print "send a get for id "
        println id

        response = client.get(path: test)

        then: 'server returns 200 code'
        assert response.status == 200
        /*response.getData().each { entry ->
            if (entry.key == "_id"){
                assert entry.value == '5e6ec9adbbc83700179f3b1b'
            }
        }*/

        println response.data['_id']
        assert response.data['_id'] == id

        where:
        id << ["5e6ec9adbbc83700179f3b1b" , "5e6e2ef65c94c6001795db7f", "5e6d5a4573faf30017fea25c" ]

        /*id                             | testing
        "5e6ec9adbbc83700179f3b1b"     | 0
        "5e6e2ef65c94c6001795db7f"     | 0
        "5e6d5a4573faf30017fea25c"     | 0*/


    }
}


//As a manager I want an API for user login
/*
given:
  a user name
  a password

where:

  send an API auth with user and pass

then:
  receive 200 ok
 */



class APIRestullBooker extends Specification {

    @Shared
    def client = new RESTClient('https://restful-booker.herokuapp.com')

    def 'ping'() {
        when: 'get random jokes'
        HttpResponseDecorator response = client.get(path: '/ping')

        then: 'server returns 201 code (created)'
        //assert response.responseBase.statusLine.statusCode == 200
        //assert response.responseBase.statusLine.toString().contains('203')
        assert response.status == 200
    }


    def 'should return 200 code and Bad Credentials'() {

        when: 'send a POST auth with error'
        def response = client.post(path: '/auth')

        then: 'server returns 200 code (ok)'
        HttpResponseException e = thrown(HttpResponseException)
        assert e.response.status == 404: 'response code should be 404'

    }

    def 'should return 200 code'() {

        when: 'send a POST auth with error'
        def response = client.post(path: '/auth',
                                body:  [username : "admin" ,
                                        password: "password123"],
                                requestContentType : JSON)

        then: 'server returns 200 code (ok)'
        assert response.status == 200

    }

    //https://blog.j-labs.pl/2019/05/Test-your-REST-API-with-Spock-Introduction-to-Spock-Framework
}
