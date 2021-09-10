package designmode;

public class fractoryMethodPattern {
    public static void main(String[] args) {
        Logger logger;
        logger = new dataFactory().createLog();
        logger.writeLogger();
    }
}

interface Logger {
    void writeLogger();
}

class dataLog implements Logger {

    @Override
    public void writeLogger() {
        System.out.println("写入数据库记录");
    }

}

class fileLog implements Logger {

    @Override
    public void writeLogger() {
        System.out.println("写入文件记录");
    }
    
}


interface Factory {
    Logger createLog();
}

class dataFactory implements Factory {

    @Override
    public Logger createLog() {
        System.out.println("创建数据库记录");
        Logger logger = new dataLog();
        return logger;
    }
    
}

class fileFactory implements Factory {

    @Override
    public Logger createLog() {
        System.out.println("创建文件记录");
        Logger logger = new fileLog();
        return logger;
    }

}