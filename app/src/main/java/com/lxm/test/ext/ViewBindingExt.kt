package com.lxm.test.ext

import android.view.LayoutInflater
import android.view.View
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * @link https://github.com/hi-dhl/Binding
 * @link https://7449.github.io/2021/01/04/viewbinding.html
 * @link https://github.com/android/architecture-components-samples/tree/master/ViewBindingSample/app/src/main/java/com/android/example/viewbindingsample
 * @link https://juejin.cn/post/6954276809508651022
 * @link https://juejin.cn/post/6960914424865488932
 * @link https://juejin.cn/post/6950530267547172901
 */
/**
 * AppCompatActivity使用中
 * <pre>
 * // example
 *  class MainActivity : AppCompatActivity() {
 *       private val binding by viewBinding(ActivityMainBinding::inflate)
 *
 *       override fun onCreate(savedInstanceState: Bundle?) {
 *           super.onCreate(savedInstanceState)
 *
 *           // 确保调用该函数设置binding.root
 *           setContentView(binding.root)
 *       }
 *   }
 * </pre>
 */
@MainThread
inline fun <reified VB : ViewBinding> AppCompatActivity.viewBinding(
    noinline inflater: (LayoutInflater) -> VB
) = lazy {
    inflater(layoutInflater).also { setContentView(it.root) }
}

/**
 *  Fragment中使用
 *  private val binding by viewBinding(FragmentHomeBinding::bind)
 *   * <pre>
 * // example
 *  class HomeFragment : Fragment(R.layout.fragment_home) {
 *      private val binding by viewBinding(FragmentHomeBinding::bind)
 *
 *       override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
 *          super.onViewCreated(view, savedInstanceState)
 *            binding.textHome.text = "example"
 *
 *       }
 *   }
 * </pre>
 */
@MainThread
inline fun <reified VB : ViewBinding> viewBinding(noinline bindView: (View) -> VB) =
    ViewBindingDelegate(bindView)