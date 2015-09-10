package ds.features.ui.reveal;

import android.animation.Animator;
import android.graphics.Point;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

/**
 * Created by lintonye on 14-12-02.
 */
public class RevealTransition extends Visibility {

	private final Point mEpicenter;
	private final int mSmallRadius;
	private final int mBigRadius;
	private final long mDuration;


	public RevealTransition(Point epicenter, int smallRadius, int bigRadius, long duration) {
		mEpicenter = epicenter;
		mSmallRadius = smallRadius;
		mBigRadius = bigRadius;
		mDuration = duration;
	}


	@Override
	public Animator onAppear(ViewGroup sceneRoot, final View view, TransitionValues startValues, TransitionValues endValues) {
		Animator animator = ViewAnimationUtils.createCircularReveal(view, mEpicenter.x, mEpicenter.y, mSmallRadius, mBigRadius);
		//view.setVisibility(View.VISIBLE);
		animator.setDuration(mDuration);
		/*view.setAlpha(0f);
		animator.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(final Animator animation) {
				view.setAlpha(1f);
			}
		});*/
		return new WrapperAnimator(animator);
	}


	@Override
	public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
		Animator animator = ViewAnimationUtils.createCircularReveal(view, mEpicenter.x, mEpicenter.y, mBigRadius, mSmallRadius);
		animator.setDuration(mDuration);
		return new WrapperAnimator(animator);
	}


}