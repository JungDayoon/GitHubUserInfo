package com.example.githubuserinfo;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleObserver;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class DisposeBag implements LifecycleObserver {
	private static final String DEFAULT_EXCLUSIVE_TAG = "defaultExclusive";

	private CompositeDisposable compositeDisposable;
	private Map<String, Disposable> exclusiveDisposableMap;

	private boolean valid = true;

	public DisposeBag() {
		reset();
	}

	private void reset() {
		compositeDisposable = new CompositeDisposable();
		exclusiveDisposableMap = new HashMap<>();
	}

	public void add(@Nullable Disposable disposable) {
		if (disposable == null) {
			return;
		}

		if (!isValid()) {
			disposable.dispose();
			return;
		}
		compositeDisposable.add(disposable);
	}

	public void add(Disposable... disposables) {
		for (Disposable disposable : disposables) {
			add(disposable);
		}
	}

	public void addExclusive(Disposable disposable) {
		addExclusive(disposable, DEFAULT_EXCLUSIVE_TAG);
	}

	public void addExclusive(Disposable disposable, String tag) {
		if (!isValid()) {
			disposable.dispose();
			return;
		}

		registerExclusiveDisposable(disposable, tag);
	}

	public void dispose() {
		if (!compositeDisposable.isDisposed()) {
			compositeDisposable.dispose();
		}
		reset();
		valid = false;
	}

	public void clear() {
		dispose();
		setValid(true);
	}

	private void registerExclusiveDisposable(Disposable disposable, String tag) {
		if (exclusiveDisposableMap.containsKey(tag)) {
			Disposable prevDisposable = exclusiveDisposableMap.remove(tag);
			compositeDisposable.remove(prevDisposable);
			if (!prevDisposable.isDisposed()) {
				prevDisposable.dispose();
			}
		}

		exclusiveDisposableMap.put(tag, disposable);
		compositeDisposable.add(disposable);
	}

	protected void setValid(boolean valid) {
		this.valid = valid;
	}

	public boolean isValid() {
		return valid;
	}
}
