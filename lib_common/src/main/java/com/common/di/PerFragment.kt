package com.common.di
 
import javax.inject.Scope
 
/**
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the Activity to be memorised in the
 * correct component.
 */
@Scope
@kotlin.annotation.Retention
annotation class PerFragment