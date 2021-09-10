package designmode;


public class proxyPattern {
    public static void main(String[] args) {
        SearchProxy searchProxy = new SearchProxy();
        String res = searchProxy.doSearch("杨过", "111");
        System.out.println(res.toString());
    }    
}

interface Search {
    String doSearch(String name, String keyword);
}

class AccessValidator {
    public boolean Validate(String name) {
        System.out.println("在数据库中验证数据");
        if (name.equals("杨过")) {
            System.out.println("验证成功");
            return true;
        } else {
            System.out.println("验证失败");
            return false;
        }
    }
}

class Log {
    public void log(String name) {
        System.out.printf("更新数据库,用户%s查询数次加一\n", name);
    }
}

class RealSearch implements Search {

    @Override
    public String doSearch(String name, String keyword) {
        System.out.printf("用户%s使用关键词%s查询信息\n", name, keyword);
        return "具体内容";
    }

}

class SearchProxy implements Search {

    private RealSearch search;
    private AccessValidator validator;
    private Log logger;


    @Override
    public String doSearch(String name, String keyword) {
        if (search == null) {search = new RealSearch();}
        if (doValidate(name)) {
            String result = search.doSearch(name, keyword);
            doLog(name);
            return result;
        } else {
            return null;
        }
    }

    public boolean doValidate(String name) {
        validator = new AccessValidator();
        return validator.Validate(name);
    }

    public void doLog(String name) {
        logger = new Log();
        logger.log(name);
    }

}
