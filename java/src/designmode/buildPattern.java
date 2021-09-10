package designmode;

public class buildPattern {
    
}

class Actor {
    private String type;
    private String sex;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }  
}

abstract class ActorBuilder {
    protected Actor actor = new Actor();
    public abstract void builderType();
    public abstract void buildSex();
    public Actor createActor() {return actor;}
}

class EvilActor extends ActorBuilder {

    @Override
    public void builderType() {
       actor.setType("恶魔");
        
    }

    @Override
    public void buildSex() {
       actor.setSex("男");
        
    }

}

class AngleActor extends ActorBuilder {

    @Override
    public void builderType() {
        actor.setType("天使");
        
    }

    @Override
    public void buildSex() {
        actor.setSex("女");
        
    }

}

class BulidController {
    public Actor construct(ActorBuilder ab) {
        Actor actor;
        ab = new EvilActor();
        ab.builderType();
        ab.buildSex();
        actor = ab.createActor();
        return actor;
    }
}
