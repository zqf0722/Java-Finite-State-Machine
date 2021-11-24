import java.io.*;
import java.util.*;
import Fsm.FSM;
import Fsm.*;



class My_States extends State{
    public My_States(String name){
        super(name);
    }
}

class ValidEvent{
    static Set<String> validevt = new HashSet<String>();
    public ValidEvent(){
        String s = "PASSIVE";
        validevt.add(s);
        s = "ACTIVE";
        validevt.add(s);
        s = "SYN";
        validevt.add(s);
        s = "SYNACK";
        validevt.add(s);
        s = "ACK";
        validevt.add(s);
        s = "RDATA";
        validevt.add(s);
        s = "SDATA";
        validevt.add(s);
        s = "FIN";
        validevt.add(s);
        s = "CLOSE";
        validevt.add(s);
        s = "TIMEOUT";
        validevt.add(s);
    }

    public boolean IsValid(String evt){
        if(validevt.contains(evt)) return true;
        else return false;
    }
}

class My_Events extends Event{
    public My_Events(String name){
        super(name);
    }
}

class My_Action extends Action {
    static int rtimes = 0;
    static int stimes = 0;
    String action = "";
    public My_Action(String name){
        this.action = name;
    }

    public void execute(FSM var1, Event var2) {
        String event_name = var2.getName();
        State state = var1.currentState();
        String state_name = state.getName();
        if (event_name.equals("RDATA") || event_name.equals("SDATA")) {
            if (event_name.equals("RDATA")) {
                System.out.println(String.format("Data received %d", ++rtimes));
            } else {
                System.out.println(String.format("Data sent %d", ++stimes));
            }
        } else {
            if(this.action==""){
                System.out.println(String.format("Event %s received, current State is %s, no action.", event_name, state_name));
            }else{
                System.out.println(String.format("Event %s received, current State is %s, did action %s",
                        event_name, state_name, this.action));
            }
        }
    }

    public void reset(){
        rtimes = 0;
        stimes = 0;
    }
}

public class Myfsm {
    static My_States start_state = new My_States("CLOSED");
    static FSM my_fsm = new FSM("TCP", start_state);
    static Set<List<String>> ttable = new HashSet<List<String>>();
    static ValidEvent validname = new ValidEvent();

    public static void ManuallyCreateTranTable(){
        List<String> sepair = null;
        String cs = "CLOSED";
        String ns = "LISTEN";
        String evt = "PASSIVE";
        String act = "";
        sepair = new ArrayList<String>();
        sepair.add(cs);
        sepair.add(ns);
        sepair.add(evt);
        sepair.add(act);
        ttable.add(sepair);
        cs = "LISTEN";
        ns = "CLOSED";
        evt = "CLOSE";
        act = "";
        sepair = new ArrayList<String>();
        sepair.add(cs);
        sepair.add(ns);
        sepair.add(evt);
        sepair.add(act);
        ttable.add(sepair);
        cs = "CLOSED";
        ns = "SYN_SENT";
        evt = "ACTIVE";
        act = "syn";
        sepair = new ArrayList<String>();
        sepair.add(cs);
        sepair.add(ns);
        sepair.add(evt);
        sepair.add(act);
        ttable.add(sepair);
        cs = "LISTEN";
        ns = "SYN_RCVD";
        evt = "SYN";
        act = "syn-ack";
        sepair = new ArrayList<String>();
        sepair.add(cs);
        sepair.add(ns);
        sepair.add(evt);
        sepair.add(act);
        ttable.add(sepair);
        cs = "SYN_SENT";
        ns = "CLOSED";
        evt = "CLOSE";
        act = "";
        sepair = new ArrayList<String>();
        sepair.add(cs);
        sepair.add(ns);
        sepair.add(evt);
        sepair.add(act);
        ttable.add(sepair);
        cs = "SYN_SENT";
        ns = "SYN_RCVD";
        evt = "SYN";
        act = "syn-ack";
        sepair = new ArrayList<String>();
        sepair.add(cs);
        sepair.add(ns);
        sepair.add(evt);
        sepair.add(act);
        ttable.add(sepair);
        cs = "SYN_SENT";
        ns = "ESTABLISHED";
        evt = "SYNACK";
        act = "ack";
        sepair = new ArrayList<String>();
        sepair.add(cs);
        sepair.add(ns);
        sepair.add(evt);
        sepair.add(act);
        ttable.add(sepair);
        cs = "SYN_RCVD";
        ns = "ESTABLISHED";
        evt = "ACK";
        act = "";
        sepair = new ArrayList<String>();
        sepair.add(cs);
        sepair.add(ns);
        sepair.add(evt);
        sepair.add(act);
        ttable.add(sepair);
        cs = "SYN_RCVD";
        ns = "FIN_WAIT_1";
        evt = "CLOSE";
        act = "fin";
        sepair = new ArrayList<String>();
        sepair.add(cs);
        sepair.add(ns);
        sepair.add(evt);
        sepair.add(act);
        ttable.add(sepair);
        cs = "ESTABLISHED";
        ns = "ESTABLISHED";
        evt = "RDATA";
        act = "";
        sepair = new ArrayList<String>();
        sepair.add(cs);
        sepair.add(ns);
        sepair.add(evt);
        sepair.add(act);
        ttable.add(sepair);
        cs = "ESTABLISHED";
        ns = "ESTABLISHED";
        evt = "SDATA";
        act = "";
        sepair = new ArrayList<String>();
        sepair.add(cs);
        sepair.add(ns);
        sepair.add(evt);
        sepair.add(act);
        ttable.add(sepair);
        cs = "ESTABLISHED";
        ns = "FIN_WAIT_1";
        evt = "CLOSE";
        act = "fin";
        sepair = new ArrayList<String>();
        sepair.add(cs);
        sepair.add(ns);
        sepair.add(evt);
        sepair.add(act);
        ttable.add(sepair);
        cs = "ESTABLISHED";
        ns = "CLOSE_WAIT";
        evt = "FIN";
        act = "ack";
        sepair = new ArrayList<String>();
        sepair.add(cs);
        sepair.add(ns);
        sepair.add(evt);
        sepair.add(act);
        ttable.add(sepair);
        cs = "FIN_WAIT_1";
        ns = "FIN_WAIT_2";
        evt = "ACK";
        act = "";
        sepair = new ArrayList<String>();
        sepair.add(cs);
        sepair.add(ns);
        sepair.add(evt);
        sepair.add(act);
        ttable.add(sepair);
        cs = "FIN_WAIT_1";
        ns = "CLOSING";
        evt = "FIN";
        act = "ack";
        sepair = new ArrayList<String>();
        sepair.add(cs);
        sepair.add(ns);
        sepair.add(evt);
        sepair.add(act);
        ttable.add(sepair);
        cs = "FIN_WAIT_2";
        ns = "TIME_WAIT";
        evt = "FIN";
        act = "ack";
        sepair = new ArrayList<String>();
        sepair.add(cs);
        sepair.add(ns);
        sepair.add(evt);
        sepair.add(act);
        ttable.add(sepair);
        cs = "CLOSING";
        ns = "TIME_WAIT";
        evt = "ACK";
        act = "";
        sepair = new ArrayList<String>();
        sepair.add(cs);
        sepair.add(ns);
        sepair.add(evt);
        sepair.add(act);
        ttable.add(sepair);
        cs = "TIME_WAIT";
        ns = "CLOSED";
        evt = "TIMEOUT";
        act = "";
        sepair = new ArrayList<String>();
        sepair.add(cs);
        sepair.add(ns);
        sepair.add(evt);
        sepair.add(act);
        ttable.add(sepair);
        cs = "CLOSE_WAIT";
        ns = "LAST_ACK";
        evt = "CLOSE";
        act = "fin";
        sepair = new ArrayList<String>();
        sepair.add(cs);
        sepair.add(ns);
        sepair.add(evt);
        sepair.add(act);
        ttable.add(sepair);
        cs = "LAST_ACK";
        ns = "CLOSED";
        evt = "ACK";
        act = "";
        sepair = new ArrayList<String>();
        sepair.add(cs);
        sepair.add(ns);
        sepair.add(evt);
        sepair.add(act);
        ttable.add(sepair);
    }

    public static void BuildFsm(FSM my_fsm){
        for(List<String> table:ttable){
            String css = table.get(0);
            String nss = table.get(1);
            String evts = table.get(2);
            String acts = table.get(3);
            My_States cs = new My_States(css);
            My_States ns = new My_States(nss);
            My_Events evt = new My_Events(evts);
            My_Action act = new My_Action(acts);
            Transition t = new Transition(cs, evt, ns, act);
            try{
                my_fsm.addTransition(t);
            } catch (FsmException fsm){
                System.out.println(fsm);
            }
        }
    }

    public static void main (String[] args) {
        if (args.length == 0) {
            System.out.println("no input file detecting");
        }
        ManuallyCreateTranTable();
        BuildFsm(my_fsm);
        for (String fileName : args) {
            my_fsm.reset();
            My_Action act = new My_Action("");
            act.reset();
            Dofile(fileName);
        }
    }

    public static void Dofile(String fileName){
        Scanner fileln = null;
        System.out.println("Scanning file: "+fileName);
        try{
            fileln = new Scanner(new FileReader(fileName));
            while(fileln.hasNext()){
                String event = fileln.next();
                if(!validname.IsValid(event)){
                    System.out.println("Error: unexpected Event: "+event);
                    continue;
                }
                My_Events cur_event = new My_Events(event);
                    try{
                    my_fsm.doEvent(cur_event);
                } catch (FsmException fsm){
                    System.out.println(fsm);
                }
            }
        }
        catch (IOException ioe){
            System.out.println(ioe);
        }
    }
}