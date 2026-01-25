/*
 * Copyright (C) 2026 ._______166
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.notification.modes;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.preference.Preference;
import androidx.preference.TwoStatePreference;

import com.android.settingslib.notification.modes.ZenMode;
import com.android.settingslib.notification.modes.ZenModesBackend;

class VibrateOnDNDPreferenceController extends AbstractZenModePreferenceController
        implements Preference.OnPreferenceChangeListener {

    public VibrateOnDNDPreferenceController(Context context, String key,
            ZenModesBackend backend) {
        super(context, key, backend);
    }

    @Override
    public void updateState(Preference preference, @NonNull ZenMode zenMode) {
        preference.setEnabled(zenMode.isEnabled() && zenMode.canEditPolicy());
        ((TwoStatePreference) preference).setChecked(zenMode.isVibrateEnabled());
    }

    @Override
    public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
        final boolean vibrateOnEnable = ((Boolean) newValue);
        return saveMode(zenMode -> {
            zenMode.setVibrateEnabled(vibrateOnEnable);
            return zenMode;
        });
    }
}
