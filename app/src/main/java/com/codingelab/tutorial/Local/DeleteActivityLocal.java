package com.codingelab.tutorial.Local;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.codingelab.tutorial.R;
import com.codingelab.tutorial.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeleteActivityLocal extends AppCompatActivity {
    DBHelper mydb;
    public static int dell;
    private ArrayList<Student> studentlist;
    Button delete;
    EditText finde;
     ListView lview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_local);
        setTitle("Delete Local");
        mydb = new DBHelper(this);


        delete = (Button)findViewById(R.id.bttnDeleteL);

        finde = (EditText)findViewById(R.id.editFind);
        mydb = new DBHelper(this);
        reloead();
        studentlist = new ArrayList<Student>();



//        listViewAdapter adapter = new listViewAdapter(this, studentlist);

       /* final ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();

        List<Student> data = mydb.getAllcontactDetails();
        for (Student val :data)
        {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", String.valueOf(val.getId()));
            map.put("name", val.getName());
            map.put("phone", val.getPhone());
            map.put("email", val.getEmail());
            Items.add(map);
        }

        ListAdapter myadapter = new SimpleAdapter(this, Items,
                R.layout.listview_rows,new String[] { "id", "name", "phone", "email"},
                new int[] {R.id.idText, R.id.NameText, R.id.PhoneText, R.id.EmailText});

        lview.setAdapter(myadapter);*/
        lview = (ListView) findViewById(R.id.listViewDL);
        lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String sid = ((TextView)view.findViewById(R.id.idText)).getText().toString();
               // final TextView idText = view.findViewById(R.id.idText);
                //final String idGetText = idText.getText().toString();

                AlertDialog.Builder alert = new AlertDialog.Builder(DeleteActivityLocal.this);
                alert.setTitle("Delete!");
                alert.setMessage("Do you Sure want delete ?");
                alert.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //remove(idGetText);
                               // mydb.getDataDelete(Integer.parseInt(sid));
                                mydb.deleteContact(Integer.parseInt(sid));
                                reloead();
                            }
                        });

                alert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DeleteActivityLocal.this, "No", Toast.LENGTH_SHORT).show();
                            }
                        });
                alert.show();

            }
        });

        /*lview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String sid = ((TextView)view.findViewById(R.id.idText)).getText().toString();
                String name = ((TextView)view.findViewById(R.id.NameText)).getText().toString();
                String phone = ((TextView)view.findViewById(R.id.PhoneText)).getText().toString();
                String email = ((TextView)view.findViewById(R.id.EmailText)).getText().toString();
                dell = Integer.parseInt(sid);
                Cursor d=mydb.getDataDelete(dell);
                if(!d.moveToNext())
                {
                    Toast.makeText(getApplicationContext(), "finish delete", Toast.LENGTH_LONG).show();
                    Intent activity = new Intent(DeleteActivityLocal.this, DeleteActivityLocal.class);
                    startActivity(activity);
                }else{
                    Toast.makeText(getApplicationContext(),"no dolete", Toast.LENGTH_LONG).show();
                }


            }
        });*/

        /*lview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View view,
                                           final int pos, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(DeleteActivityLocal.this);
                //final TextView idText = view.findViewById(R.id.FirstText);
                final String idTextt = ((TextView)view.findViewById(R.id.idText)).getText().toString();
                alert.setTitle("Delete!");
                alert.setMessage("Do you really want delete ?");
                alert.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dell = Integer.parseInt(idTextt);
                                System.out.println("==========================================================");
                                System.out.println(idTextt);
                                //mydb.deleteContact(Integer.parseInt(idTextt));
                                Cursor ch = mydb.getDataDelete(Integer.parseInt(idTextt));
                                if(!ch.moveToNext()){
                                    System.out.println("==========================================================");
                                    System.out.println("ok");
                                    System.out.println("==========================================================");
                                }else{
                                    System.out.println("==========================================================");
                                    System.out.println("No");
                                    System.out.println("==========================================================");
                                }
                            }

                        });
                alert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DeleteActivityLocal.this, "No", Toast.LENGTH_SHORT).show();
                            }
                        });


                alert.show();

                return true;
            }
        });*/


        ////////////////////////////////////////////////////////////////////////////////////////////
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int found = Integer.parseInt(finde.getText().toString());
                if (isEmpty(finde)) {
                    finde.setError("Enter Corrcet ID");
                }  else {

                    Cursor getRecord = mydb.getDataDelete(found);
                    if (!getRecord.moveToNext()) {
                        Toast.makeText(getApplicationContext(), "finish delete", Toast.LENGTH_LONG).show();
                        reloead();
                        finde.setText("");
                        //Intent activity = new Intent(DeleteActivityLocal.this, DeleteActivityLocal.class);
                        //startActivity(activity);
                    } else {
                        Toast.makeText(getApplicationContext(), "no dolete", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }
    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    void reloead(){
        final ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
        lview = (ListView) findViewById(R.id.listViewDL);
        List<Student> data = mydb.getAllcontactDetails();
        for (Student val :data)
        {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", String.valueOf(val.getId()));
            map.put("name", val.getName());
            map.put("phone", val.getPhone());
            map.put("email", val.getEmail());
            Items.add(map);
        }

        ListAdapter myadapter = new SimpleAdapter(this, Items,
                R.layout.listview_rows,new String[] { "id", "name", "phone", "email"},
                new int[] {R.id.idText, R.id.NameText, R.id.PhoneText, R.id.EmailText});

        lview.setAdapter(myadapter);
    }
}
