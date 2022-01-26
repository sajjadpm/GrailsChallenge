package my.app


import grails.rest.*
import grails.converters.*

class UserController extends RestfulController {
    def userService;
    static responseFormats = ['json', 'xml']
    UserController() {
        super(User)
    }

    def fileUpload() {
        def f = request.getFile('File')
        String filePath = request.getSession().getServletContext().getRealPath("") + 'myfile.txt'
        boolean status = userService.upload(f, filePath);
        if (status) {
            def persiststatus =  userService.persistFileDetails(filePath);
            response.setHeader('Access-Control-Allow-Origin', request.getHeader("Origin"))
            response.setHeader('Access-Control-Allow-Methods', 'POST, PUT, GET, OPTIONS, PATCH')
            response.setHeader('Access-Control-Allow-Headers', 'X-Additional-Headers-Example')
            response.setHeader('Access-Control-Allow-Credentials', 'true')
            response.setHeader('Access-Control-Max-Age', '1728000')
            if (persiststatus != true){
                response.sendError(400,'Error at line number '+persiststatus)
            }
            else {
                response.sendError(200, 'Success');
            }
        }
        else {
            response.sendError(500, 'Error')
        }
    }
}

