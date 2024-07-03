package app.data;

public class filter{
    
    public static boolean hasXSS(String rawinput){
        String input = rawinput.toLowerCase();
        String payload[] = {"alert(","<script>","</script>","onclick=","onerror="};
        for (String pattern : payload){
            if (input.contains(pattern)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasSQLinjection(String rawinput){
        String input = rawinput.toLowerCase();
        String payload[] = {"'","\"","union","select","from *","information_schema",".columns","tables","database()","user()","order by"};
        for (String pattern : payload){
            if (input.contains(pattern)) {
                return true;
            }
        }
        return false;
    }
}
