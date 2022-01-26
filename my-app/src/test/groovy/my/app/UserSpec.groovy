package my.app

import grails.test.hibernate.HibernateSpec
import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
@SuppressWarnings(['MethodName', 'DuplicateNumberLiteral'])
class UserSpec extends HibernateSpec{

    void 'test domain class validation'() {
        when: 'User domain class is saved with invalid data'
        User user = new User(userId: '1234566', userName:'',coin : new Coin(coins:5))
        user.save()

        then: 'There were errors and the data was not saved'
        user.hasErrors()
        user.errors.getFieldError('userName')
        user.count() == 0

        when: 'A valid User is saved'
        user.userName = 'Tim'
        user.userId = '24324324'
        user.coin   = new Coin(coins: 100)
        user.save()

        then: 'The product was saved successfully'
        user.count() == 1
        user.first().userId == '24324324'
    }



}
