package net.imglib2.stream;

import java.util.Spliterator;
import java.util.function.Consumer;
import net.imglib2.LocalizableSampler;
import net.imglib2.Positionable;
import net.imglib2.RealPositionable;

class LocalizableSamplerWrapper< T > implements Spliterator< LocalizableSampler< T > >, LocalizableSampler< T >
{
	private final LocalizableSpliterator< T > delegate;

	LocalizableSamplerWrapper( final LocalizableSpliterator< T > delegate )
	{
		this.delegate = delegate;
	}

	@Override
	public T get()
	{
		return delegate.get();
	}

	@Override
	public LocalizableSamplerWrapper< T > copy()
	{
		return new LocalizableSamplerWrapper<>( delegate.copy() );
	}

	@Override
	public void forEachRemaining( final Consumer< ? super LocalizableSampler< T > > action )
	{
		delegate.forEachRemaining( () -> action.accept( this ) );
	}

	@Override
	public boolean tryAdvance( final Consumer< ? super LocalizableSampler< T > > action )
	{
		if ( delegate.tryAdvance() )
		{
			action.accept( this );
			return true;
		}
		else
			return false;
	}

	@Override
	public Spliterator< LocalizableSampler< T > > trySplit()
	{
		final LocalizableSpliterator< T > prefix = delegate.trySplit();
		return prefix == null ? null : new LocalizableSamplerWrapper<>( prefix );
	}

	@Override
	public long estimateSize()
	{
		return delegate.estimateSize();
	}

	@Override
	public int characteristics()
	{
		return delegate.characteristics();
	}


	// -----------------------------------------------------------
	//   Localizable

	@Override
	public int numDimensions()
	{
		return delegate.numDimensions();
	}

	@Override
	public void localize( final int[] position )
	{
		delegate.localize( position );
	}

	@Override
	public void localize( final float[] position )
	{
		delegate.localize( position );
	}

	@Override
	public void localize( final double[] position )
	{
		delegate.localize( position );
	}

	@Override
	public void localize( final long[] position )
	{
		delegate.localize( position );
	}

	@Override
	public void localize( final Positionable position )
	{
		delegate.localize( position );
	}

	@Override
	public void localize( final RealPositionable position )
	{
		delegate.localize( position );
	}

	@Override
	public int getIntPosition( final int d )
	{
		return delegate.getIntPosition( d );
	}

	@Override
	public long getLongPosition( final int d )
	{
		return delegate.getLongPosition( d );
	}

	@Override
	public float getFloatPosition( final int d )
	{
		return delegate.getFloatPosition( d );
	}

	@Override
	public double getDoublePosition( final int d )
	{
		return delegate.getDoublePosition( d );
	}
}
