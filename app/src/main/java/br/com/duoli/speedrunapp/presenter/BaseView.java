package br.com.duoli.speedrunapp.presenter;

public interface BaseView {

    void displayLoading();

    void hideLoading();

    void displayNotFound();

    void hideNotFound();

    void displayError();

    void hideError();

}
