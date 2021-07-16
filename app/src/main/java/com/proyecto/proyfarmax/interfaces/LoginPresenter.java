package com.proyecto.proyfarmax.interfaces;

public interface LoginPresenter {
    void onDestroy();
    void signInUser(String email, String password);
    void enviarRecuperarPassword();
    void enviarNuevoUsuario();

}
