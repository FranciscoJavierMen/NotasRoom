package com.example.mynotes.ui.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.mynotes.R;
import com.example.mynotes.db.entity.NotaEntity;
import com.google.android.material.textfield.TextInputLayout;

public class DialogFragmentNuevaNota extends DialogFragment {
    private View view;

    private TextInputLayout edtTitulo, edtNota;
    private RadioGroup radioColores;
    private Switch switchFavorito;

    public static DialogFragmentNuevaNota newInstance() {
        return new DialogFragmentNuevaNota();
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.add_nota)
                .setMessage(R.string.nota_message)
                .setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String titulo = edtTitulo.getEditText().getText().toString();
                        String nota = edtNota.getEditText().getText().toString();
                        String color = "azul";
                        switch (radioColores.getCheckedRadioButtonId()){
                            case R.id.radioRojo:
                                color = "rojo"; break;
                            case R.id.radioAzul:
                                color = "azul"; break;
                            case R.id.radioVerde:
                                color = "verde"; break;
                            case R.id.radioBlanco:
                                color = "blanco"; break;
                        }

                        boolean isfavorito = switchFavorito.isChecked();
                        //Comunicar al viewmodel el nuevo dato
                        DialogFragmentNuevaNotaViewModel mViewModel = ViewModelProviders.of(getActivity()).get(DialogFragmentNuevaNotaViewModel.class);
                        mViewModel.insertNota(new NotaEntity(titulo, nota, isfavorito, color));
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.dialog_fragment_nueva_nota_fragment, null);


        edtTitulo = view.findViewById(R.id.edtTituloNota);
        edtNota = view.findViewById(R.id.edtContenidoNota);
        radioColores = view.findViewById(R.id.radioGroupColores);
        switchFavorito = view.findViewById(R.id.switchFavorito);

        builder.setView(view);
        return builder.create();

    }
}
