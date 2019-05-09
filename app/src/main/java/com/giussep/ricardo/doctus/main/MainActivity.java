package com.giussep.ricardo.doctus.main;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.giussep.ricardo.doctus.R;
import com.giussep.ricardo.doctus.adapters.TipsRecyclerAdapter;
import com.giussep.ricardo.doctus.crearTip.CrearTipActivity;
import com.giussep.ricardo.doctus.models.Tip;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements MainContracts.View, TipsRecyclerAdapter.OnTipsRecyclerAdapter {

    private static final int RC_CREAR_TIP = 9507;
    @Inject
    MainContracts.Presenter presenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private ProgressDialog progressDialog;
    private TipsRecyclerAdapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter.setView(this);
        presenter.getTips();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.crear_tip:
                presenter.crearTipPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_CREAR_TIP) {
            if (resultCode == RESULT_OK) {
                presenter.getTips();
            }
        }
    }

    //region Implementation MainContracts.View

    @Override
    public void showDialogProgress() {
        progressDialog = new ProgressDialog(this);

        progressDialog.setIndeterminate(true);
        progressDialog.setTitle(R.string.app_name);
        progressDialog.setMessage(getString(R.string.loading_tips));
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        progressDialog.show();
    }

    @Override
    public void hideDialogProgress() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void loadTips(List<Tip> tipList) {
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new TipsRecyclerAdapter(tipList, this);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public void showErrorLoadingTips() {
        Toast.makeText(this, R.string.error_loading_tips, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openCrearTipActivity() {
        Intent intent = new Intent(this, CrearTipActivity.class);

        startActivityForResult(intent, RC_CREAR_TIP);
    }

    //endregion

    //region Implementation OnTipsRecyclerAdapter

    @Override
    public void onEditarPressed(Tip tip) {
        Intent intent = new Intent(this, CrearTipActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable(CrearTipActivity.TIP_EDITAR, tip);

        intent.putExtras(bundle);

        startActivityForResult(intent, RC_CREAR_TIP);
    }

    @Override
    public void onEliminarPressed(Tip tip, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Eliminar Tip");
        builder.setMessage("¿Estás seguro de eliminar este tip?");
        builder.setPositiveButton("Sí, eliminar", (dialog, which) -> {
            presenter.eliminarTip(tip, position);
            dialog.dismiss();
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());

        builder.show();
    }

    @Override
    public void showTipDeletedSuccessfully(int position) {
        Toast.makeText(this, R.string.tip_deleted_successfully, Toast.LENGTH_SHORT).show();

        recyclerAdapter.removeTip(position);
    }

    @Override
    public void showErrorDeletingTips() {

    }

    //endregion
}
