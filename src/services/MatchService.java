package src.services;

public abstract class MatchService extends BddService {




    /*public String getParameters(){
        return "{\"player1\": \""+this.id+"\", \"player2\": \""+this.name+"\", \"scoreP1\": \""+this.stamina+
                "\", \"scoreP2\": \""+this.power+"\", \"tournois\": \""+this.points+"\", \"ronde\": \""+this.sex+
                "\", \"num\": \""+this.num+"\", points:}";
    }

    public void addToDb(){
        this.executePost(this.url+"/newplayer", this.getParameters());
        System.out.println(this.serverResponse);

    }
    public void getFromDb(int id){
        this.executePost(this.url+"/getplayer", "{\"id\":\""+id+"\"}");
        System.out.println(this.serverResponse);
        this.serverResponse = this.serverResponse.substring(1);
        String[] elts = this.serverResponse.split(":");


    }
    public void updatePoints(){
        this.executePost(this.url+"/updatepoints", "{\"id\": \""+this.id+ "\"points\": \""+this.points+"\" ]");
    }*/
}
