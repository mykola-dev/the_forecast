package ds.features.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import ds.features.R;
import ds.features.databinding.ActivityDetailsBinding;
import ds.features.model.WeatherData;

public class DetailsActivity extends BaseActivity {

	@InjectExtra("weather")
	WeatherData weather;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		getWindow().setEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.slide_transition));
		//getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_transition));
		super.onCreate(savedInstanceState);
		Dart.inject(this);
		final ActivityDetailsBinding binder = DataBindingUtil.setContentView(this, R.layout.activity_details);
		setupToolBar();
		binder.setWeather(weather);
		binder.root.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				int cx = (v.getLeft() + v.getRight()) / 2;
				int cy = (v.getTop() + v.getBottom()) / 2;
				int initialRadius = v.getWidth();

				Animator anim = ViewAnimationUtils.createCircularReveal(binder.card, cx, cy, initialRadius,0);
				anim.addListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						binder.card.setVisibility(View.INVISIBLE);
						finish();
					}
				});
				anim.setDuration(500);
				anim.start();
			}
		});
	}


	private void animateReveal(final View v) {

	}


	@Override
	protected void toggleProgress(final boolean enable) {

	}


}