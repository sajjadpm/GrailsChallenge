package my.app

import grails.gorm.transactions.Transactional

class UserService {

    def upload(def f,String filePath){
        if (f.empty) {
           return false
        }
        f.transferTo(new File(filePath))
        return true
    }

    def parseInputFile(String filePath,List parsedList){
        def list = new File(filePath).text.readLines();
        int lineNumber = 0;
        boolean error = false;
        for (String line : list){
            lineNumber++;
            String[] values = line.split(",");
            if (values.size() == 3) {
                if (values[0].length() != 10 || values[0].find("[a-zA-Z]+") ||
                        values[1].find("[a-zA-Z]+") || values[1].contains(".") ||
                        (!values[1].find("[a-zA-Z]+") && Integer.valueOf(values[1]) < 0)) {
                    error = true;
                    break;
                } else {
                    parsedList.add(new User(userId: values[0], coin: new Coin(coins: values[1]), userName: values[2]));
                }
            }
            else{
                return lineNumber;
            }
        }
        new File(filePath).delete();
        if (error == true){
            return lineNumber;
        }
        return true;
    }

    @Transactional
    def persistFileDetails(String filePath){
        List parsedList = [];
        def status = parseInputFile(filePath,parsedList);
        if (status != true){
            return status;
        }
        else{
            for(User user :parsedList){
             user.save();
            }
        }
        return status
    }
}
