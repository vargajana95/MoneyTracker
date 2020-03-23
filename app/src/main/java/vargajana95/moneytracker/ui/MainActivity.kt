package vargajana95.moneytracker.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import vargajana95.moneytracker.MoneyTrackerApplication
import vargajana95.moneytracker.R
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainScreen {
    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (this.applicationContext as MoneyTrackerApplication).injector.inject(this)
        btnShow.setOnClickListener { mainPresenter.show() }
    }

    override fun onStart() {
        super.onStart()
        mainPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        mainPresenter.detachScreen()
    }

    override fun show(term: String) {
        Toast.makeText(this, term, Toast.LENGTH_LONG).show();
    }
}
