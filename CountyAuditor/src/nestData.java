import com.firebase.client.*;

/**
 * Created by AdamGary on 12/3/16.
 */
public class nestData {

    public static Iterable<DataSnapshot> snapshot;


    public static void main(String args[]){
        Firebase ref = new Firebase("https://votingsystem-5e175.firebaseio.com/");
        Firebase newRef = new Firebase("https://votingsystem-5e175.firebaseio.com/2016");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                snapshot = dataSnapshot.getChildren();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        try{
            Thread.sleep(7000);
        }catch(InterruptedException e){
           System.out.println("error");
        }
        for (DataSnapshot snap:snapshot
             ) {
            newRef.child(snap.getKey()).setValue(snap.getValue());

        }

    }
}
