environments {
    dev {
        jdbc {
			driver='com.mysql.jdbc.Driver'
			url='jdbc:mysql://localhost:3306/dev?characterEncoding=utf-8'
			user='root'
			password='123456'
        }
    }

    test {
        jdbc {
			driver='com.mysql.jdbc.Driver'
			url='jdbc:mysql://localhost:3306/test?characterEncoding=utf-8'
			user='root'
			password='123456'
        }
    }

    prod {
        jdbc {
			driver='com.mysql.jdbc.Driver'
			url='jdbc:mysql://localhost:3306/prod?characterEncoding=utf-8'
			user='root'
			password='123456'
        }
    }
}