package projet.fst.ma.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import projet.fst.ma.app.classes.Etudiant;
import projet.fst.ma.app.service.EtudiantService;

public class MainActivity extends AppCompatActivity {

    EditText nom, prenom, id;
    Button add, chercher, supprimer;
    TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EtudiantService es = new EtudiantService(this);

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        id = findViewById(R.id.id);

        add = findViewById(R.id.bn);
        chercher = findViewById(R.id.load);
        supprimer = findViewById(R.id.delete);
        res = findViewById(R.id.res);

        add.setOnClickListener(v -> {
            es.create(new Etudiant(
                    nom.getText().toString(),
                    prenom.getText().toString()
            ));
            Toast.makeText(this, "Ajouté", Toast.LENGTH_SHORT).show();
            nom.setText("");
            prenom.setText("");
        });

        chercher.setOnClickListener(v -> {
            String txt = id.getText().toString();

            if (txt.isEmpty()) {
                Toast.makeText(this, "Entrer ID", Toast.LENGTH_SHORT).show();
                return;
            }

            Etudiant e = es.findById(Integer.parseInt(txt));

            if (e == null) {
                res.setText("Introuvable");
            } else {
                res.setText(e.getNom() + " " + e.getPrenom());
            }
        });

        supprimer.setOnClickListener(v -> {
            String txt = id.getText().toString();

            if (txt.isEmpty()) return;

            Etudiant e = es.findById(Integer.parseInt(txt));

            if (e != null) {
                es.delete(e);
                res.setText("");
                Toast.makeText(this, "Supprimé", Toast.LENGTH_SHORT).show();
            }
        });
    }
}