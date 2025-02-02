package org.tensorflow.keras.initializers

import org.tensorflow.Operand
import org.tensorflow.keras.utils.Keras
import org.tensorflow.op.Ops
import org.tensorflow.op.core.Assign
import org.tensorflow.types.TInt32
import org.tensorflow.types.family.TNumber

abstract class Initializer {
  /**
    * Adds an `Assign` Op to the graph to initialize
    * a tensorflow variable as specified by the initializer.
    *
    * @param tf Tensorflow Ops Accessor
    * @param in Variable to initialize
    * @return Assign Operand created
    */
  def apply[T <: TNumber](tf: Ops, in: Operand[T], dtype: Class[T]): Assign[T] = tf.assign(in, this.initialize(tf, Keras.shapeOperand(tf, in.asOutput.shape), dtype))

  /**
    * Returns a Tensor object initialized as
    * specified by the initializer.
    *
    * @param tf    Tensorflow Ops Handle
    * @param shape Shape of the tensor
    */
  def initialize[T <: TNumber](tf: Ops, shape: Operand[TInt32], dtype: Class[T]): Operand[T]
}
