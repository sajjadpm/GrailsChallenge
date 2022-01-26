package my.app

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class UserServiceSpec extends Specification implements ServiceUnitTest<UserService>{


    void "parse Input file for issues"() {
        when : "Input file is having corrupt entry"
            def userService = Mock(UserService)
            def status = userService.parseInputFile("fileTest.txt", List [] as List)
        then:
            status != true
    }
}
