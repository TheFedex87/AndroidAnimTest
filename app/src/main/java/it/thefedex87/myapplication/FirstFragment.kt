package it.thefedex87.myapplication

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Button
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val leafTop: View = view.findViewById(R.id.leaf_top)
        leafTop.pivotX = 0f
        leafTop.pivotY = 0f

        val leafBottom: View = view.findViewById(R.id.leaf_bottom)
        leafBottom.pivotX = 0f
        leafBottom.pivotY = view.layoutParams.height + leafBottom.layoutParams.height * 1.0f

        val translateAnim = AnimationUtils.loadAnimation(requireActivity(), R.anim.translate_anim)
        val scaleAnim = AnimationUtils.loadAnimation(requireActivity(), R.anim.scale_animation)

        anim(leafTop, 0f, leafTop.layoutParams.height * 0.9f)
        anim(leafBottom,  view.layoutParams.height * 1.0f, view.layoutParams.height - leafBottom.layoutParams.height * 0.9f)

        //v.startAnimation(anim)
    }

    private fun anim(view: View, startPosition: Float, endPosition: Float) {
        val translateAnimator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, startPosition, endPosition).setDuration(550)
        translateAnimator.interpolator = FastOutSlowInInterpolator()
        val scaleAnimatorIn1 = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 1.5f).setDuration(300)
        scaleAnimatorIn1.startDelay = 200
        //scaleAnimatorIn.interpolator = BounceInterpolator()
        val scaleAnimatorOut1 = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.5f, 0.8f).setDuration(350)
        //scaleAnimatorOut.interpolator = BounceInterpolator()
        val scaleAnimatorIn2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.8f, 1.1f).setDuration(400)
        //scaleAnimatorIn.interpolator = BounceInterpolator()
        val scaleAnimatorOut2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.1f, 1f).setDuration(450)
        //scaleAnimatorOut2.interpolator = BounceInterpolator()

        val animatorSet = AnimatorSet()
        animatorSet.play(translateAnimator)
        animatorSet.playSequentially(scaleAnimatorIn1, scaleAnimatorOut1, scaleAnimatorIn2, scaleAnimatorOut2)
        animatorSet.startDelay = 500
        animatorSet.start()

    }
}