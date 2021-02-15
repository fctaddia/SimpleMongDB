public class Document {

    String object;
    String notes;
    boolean fbool;
    int whole;

    public Document(String object, String notes, boolean fbool, int whole) {
        this.object = object;
        this.notes = notes;
        this.fbool = fbool;
        this.whole = whole;
    }

    public org.bson.Document create() {
        return new org.bson.Document("oggetto", object)
                .append("note", notes)
                .append("fbool", fbool)
                .append("intero", whole);
    }

}
