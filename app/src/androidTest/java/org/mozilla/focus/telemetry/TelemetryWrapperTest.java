package org.mozilla.focus.telemetry;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mozilla.focus.R;
import org.mozilla.focus.activity.MainActivity;
import org.mozilla.focus.generated.LocaleList;
import org.mozilla.focus.locale.LocaleManager;
import org.mozilla.focus.locale.Locales;

import java.util.Locale;

import static org.mozilla.focus.telemetry.TelemetryWrapper.Value.AUDIO;
import static org.mozilla.focus.telemetry.TelemetryWrapper.Value.EME;
import static org.mozilla.focus.telemetry.TelemetryWrapper.Value.MIDI;
import static org.mozilla.focus.telemetry.TelemetryWrapper.Value.VIDEO;

@RunWith(AndroidJUnit4.class)
public class TelemetryWrapperTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final String prefName = context.getString(R.string.pref_key_telemetry);
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean(prefName, false).apply();

    }


    @Test
    public void toggleFirstRunPageEvent() {
        TelemetryWrapper.toggleFirstRunPageEvent(false);
    }

    @Test
    public void finishFirstRunEvent() {
        TelemetryWrapper.finishFirstRunEvent(3000000);
    }

    @Test
    public void browseIntentEvent() {
        TelemetryWrapper.browseIntentEvent();
    }

    @Test
    public void textSelectionIntentEvent() {
        TelemetryWrapper.textSelectionIntentEvent();
    }

    @Test
    public void launchByAppLauncherEvent() {
        TelemetryWrapper.launchByAppLauncherEvent();
    }

    @Test
    public void launchByHomeScreenShortcutEvent() {
        TelemetryWrapper.launchByHomeScreenShortcutEvent();
    }

    @Test
    public void launchByTextSelectionSearchEvent() {
        TelemetryWrapper.launchByTextSelectionSearchEvent();
    }

    @Test
    public void launchByExternalAppEvent() {
        TelemetryWrapper.launchByExternalAppEvent();
    }

    @Test
    public void settingsEvent() {
        activityTestRule.launchActivity(new Intent());
        SharedPreferences pm = PreferenceManager.getDefaultSharedPreferences(activityTestRule.getActivity());
        for (String key : pm.getAll().keySet()) {
            TelemetryWrapper.settingsEvent(key, Boolean.TRUE.toString());
        }

    }

    @Test
    public void settingsClickEvent() {
        activityTestRule.launchActivity(new Intent());
        SharedPreferences pm = PreferenceManager.getDefaultSharedPreferences(activityTestRule.getActivity());
        for (String key : pm.getAll().keySet()) {
            TelemetryWrapper.settingsClickEvent(key);
        }
    }

    @Test
    public void settingsLearnMoreClickEvent() {
        activityTestRule.launchActivity(new Intent());
        Context context = activityTestRule.getActivity();
        TelemetryWrapper.settingsLearnMoreClickEvent(context.getString(R.string.pref_key_turbo_mode));
        TelemetryWrapper.settingsLearnMoreClickEvent(context.getString(R.string.pref_key_telemetry));

    }

    @Test
    public void settingsLocaleChangeEvent() {
        activityTestRule.launchActivity(new Intent());
        final LocaleManager localeManager = LocaleManager.getInstance();
        final MainActivity activity = activityTestRule.getActivity();
        for (String value : LocaleList.BUNDLED_LOCALES) {
            final Locale locale;
            if (TextUtils.isEmpty(value)) {
                localeManager.resetToSystemLocale(activity);
                locale = localeManager.getCurrentLocale(activity);
            } else {
                locale = Locales.parseLocaleCode(value);
                localeManager.setSelectedLocale(activity, value);
            }
            TelemetryWrapper.settingsLocaleChangeEvent(activity.getString(R.string.pref_key_locale), String.valueOf(locale), false);
        }
    }

    @Test
    public void startSession() {
        TelemetryWrapper.startSession();
    }


    @Test
    public void stopMainActivity() {
        TelemetryWrapper.stopMainActivity();
    }

    @Test
    public void openWebContextMenuEvent() {
        TelemetryWrapper.openWebContextMenuEvent();
    }

    @Test
    public void cancelWebContextMenuEvent() {
        TelemetryWrapper.cancelWebContextMenuEvent();
    }

    @Test
    public void shareLinkEvent() {
        TelemetryWrapper.shareLinkEvent();
    }

    @Test
    public void shareImageEvent() {
        TelemetryWrapper.shareImageEvent();
    }

    @Test
    public void saveImageEvent() {
        TelemetryWrapper.saveImageEvent();
    }

    @Test
    public void copyLinkEvent() {
        TelemetryWrapper.copyLinkEvent();
    }

    @Test
    public void copyImageEvent() {
        TelemetryWrapper.copyImageEvent();
    }

    @Test
    public void addNewTabFromContextMenu() {
        TelemetryWrapper.addNewTabFromContextMenu();
    }

    @Test
    public void browseGeoLocationPermissionEvent() {
        TelemetryWrapper.browseGeoLocationPermissionEvent();
    }

    @Test
    public void browseFilePermissionEvent() {
        TelemetryWrapper.browseFilePermissionEvent();
    }

    @Test
    public void browsePermissionEvent() {
        TelemetryWrapper.browsePermissionEvent(new String[]{AUDIO, VIDEO, EME, MIDI});
    }

    @Test
    public void browseEnterFullScreenEvent() {
        TelemetryWrapper.browseEnterFullScreenEvent();
    }

    @Test
    public void browseExitFullScreenEvent() {
        TelemetryWrapper.browseExitFullScreenEvent();
    }

    @Test
    public void showMenuHome() {
        TelemetryWrapper.showMenuHome();
    }

    @Test
    public void showTabTrayHome() {
        TelemetryWrapper.showTabTrayHome();
    }

    @Test
    public void showTabTrayToolbar() {
        TelemetryWrapper.showTabTrayToolbar();
    }

    @Test
    public void showMenuToolbar() {
        TelemetryWrapper.showMenuToolbar();
    }

    @Test
    public void clickMenuDownload() {
        TelemetryWrapper.clickMenuDownload();
    }

    @Test
    public void clickMenuHistory() {
        TelemetryWrapper.clickMenuHistory();

    }

    @Test
    public void clickMenuCapture() {
        TelemetryWrapper.clickMenuCapture();

    }

    @Test
    public void showPanelDownload() {
        TelemetryWrapper.showPanelDownload();

    }

    @Test
    public void showPanelHistory() {
        TelemetryWrapper.showPanelHistory();

    }

    @Test
    public void showPanelCapture() {
        TelemetryWrapper.showPanelCapture();

    }

    @Test
    public void menuTurboChangeToTrue() {
        TelemetryWrapper.menuTurboChangeTo(true);
    }

    @Test
    public void menuTurboChangeToFalse() {
        TelemetryWrapper.menuTurboChangeTo(false);
    }

    @Test
    public void menuBlockImageChangeToTrue() {
        TelemetryWrapper.menuBlockImageChangeTo(true);
    }

    @Test
    public void menuBlockImageChangeToFalse() {
        TelemetryWrapper.menuBlockImageChangeTo(false);
    }


    @Test
    public void clickMenuClearCache() {
        TelemetryWrapper.clickMenuClearCache();

    }

    @Test
    public void clickMenuSettings() {
        TelemetryWrapper.clickMenuSettings();
    }

    @Test
    public void clickMenuExit() {
        TelemetryWrapper.clickMenuExit();
    }

    @Test
    public void clickToolbarForward() {
        TelemetryWrapper.clickToolbarForward();

    }

    @Test
    public void clickToolbarReload() {
        TelemetryWrapper.clickToolbarReload();

    }

    @Test
    public void clickToolbarShare() {
        TelemetryWrapper.clickToolbarShare();

    }

    @Test
    public void clickAddToHome() {
        TelemetryWrapper.clickAddToHome();

    }

    @Test
    public void clickToolbarCapture() {
        TelemetryWrapper.clickToolbarCapture();

    }

    @Test
    public void clickTopSiteOn() {
        TelemetryWrapper.clickTopSiteOn(0);

    }

    @Test
    public void removeTopSite() {
        TelemetryWrapper.removeTopSite(true);
        TelemetryWrapper.removeTopSite(false);
    }

    @Test
    public void addNewTabFromHome() {
        TelemetryWrapper.addNewTabFromHome();

    }

    @Test
    public void urlBarEvent() {
        TelemetryWrapper.urlBarEvent(true, true);
        TelemetryWrapper.urlBarEvent(true, false);
        TelemetryWrapper.urlBarEvent(false, true);
        TelemetryWrapper.urlBarEvent(false, false);
    }

    @Test
    public void searchSelectEvent() {
        TelemetryWrapper.searchSelectEvent();
    }

    @Test
    public void searchSuggestionLongClick() {
        TelemetryWrapper.searchSuggestionLongClick();
    }

    @Test
    public void searchClear() {
        TelemetryWrapper.searchClear();

    }

    @Test
    public void searchDismiss() {
        TelemetryWrapper.searchDismiss();

    }

    @Test
    public void showSearchBarHome() {
        TelemetryWrapper.showSearchBarHome();

    }

    @Test
    public void clickUrlbar() {
        TelemetryWrapper.clickUrlbar();
    }

    @Test
    public void clickToolbarSearch() {
        TelemetryWrapper.clickToolbarSearch();

    }

    @Test
    public void clickAddTabToolbar() {
        TelemetryWrapper.clickAddTabToolbar();

    }

    @Test
    public void clickAddTabTray() {
        TelemetryWrapper.clickAddTabTray();

    }

    @Test
    public void clickTabFromTabTray() {
        TelemetryWrapper.clickTabFromTabTray();

    }

    @Test
    public void closeTabFromTabTray() {
        TelemetryWrapper.closeTabFromTabTray();

    }

    @Test
    public void downloadRemoveFile() {
        TelemetryWrapper.downloadRemoveFile();

    }

    @Test
    public void downloadDeleteFile() {
        TelemetryWrapper.downloadDeleteFile();

    }

    @Test
    public void downloadOpenFile() {
        TelemetryWrapper.downloadOpenFile(true);
        TelemetryWrapper.downloadOpenFile(false);

    }

    @Test
    public void showFileContextMenu() {
        TelemetryWrapper.showFileContextMenu();

    }

    @Test
    public void historyOpenLink() {
        TelemetryWrapper.historyOpenLink();

    }

    @Test
    public void historyRemoveLink() {
        TelemetryWrapper.historyRemoveLink();

    }

    @Test
    public void showHistoryContextMenu() {
        TelemetryWrapper.showHistoryContextMenu();

    }

    @Test
    public void clearHistory() {
        TelemetryWrapper.clearHistory();

    }

    @Test
    public void openCapture() {
        TelemetryWrapper.openCapture();

    }

    @Test
    public void openCaptureLink() {
        TelemetryWrapper.openCaptureLink();
    }

    @Test
    public void editCaptureImage() {
        TelemetryWrapper.editCaptureImage(true);
        TelemetryWrapper.editCaptureImage(false);
    }

    @Test
    public void shareCaptureImage() {
        TelemetryWrapper.shareCaptureImage(true);
        TelemetryWrapper.shareCaptureImage(false);
    }

    @Test
    public void showCaptureInfo() {
        TelemetryWrapper.showCaptureInfo();
    }

    @Test
    public void deleteCaptureImage() {
        TelemetryWrapper.deleteCaptureImage();
    }

    @Test
    public void feedbackClickEventContextualHint() {
        TelemetryWrapper.feedbackClickEvent(TelemetryWrapper.Value.DISMISS, TelemetryWrapper.Extra_Value.CONTEXTUAL_HINTS);
        TelemetryWrapper.feedbackClickEvent(TelemetryWrapper.Value.POSITIVE, TelemetryWrapper.Extra_Value.CONTEXTUAL_HINTS);
        TelemetryWrapper.feedbackClickEvent(TelemetryWrapper.Value.NEGATIVE, TelemetryWrapper.Extra_Value.CONTEXTUAL_HINTS);

    }

    @Test
    public void feedbackClickEventSetting() {
        TelemetryWrapper.feedbackClickEvent(TelemetryWrapper.Value.DISMISS, TelemetryWrapper.Extra_Value.SETTING);
        TelemetryWrapper.feedbackClickEvent(TelemetryWrapper.Value.POSITIVE, TelemetryWrapper.Extra_Value.SETTING);
        TelemetryWrapper.feedbackClickEvent(TelemetryWrapper.Value.NEGATIVE, TelemetryWrapper.Extra_Value.SETTING);
    }

    @Test
    public void showFeedbackDialog() {
        TelemetryWrapper.showFeedbackDialog();
    }

    @Test
    public void showRateAppNotification() {
        TelemetryWrapper.showRateAppNotification();
    }

    @Test
    public void clickRateAppNotification() {
        TelemetryWrapper.clickRateAppNotification();
    }

    @Test
    public void clickRateAppNotificationPOSITIVE() {
        TelemetryWrapper.clickRateAppNotification(TelemetryWrapper.Value.POSITIVE);
    }

    @Test
    public void clickRateAppNotificationNEGATIVE() {
        TelemetryWrapper.clickRateAppNotification(TelemetryWrapper.Value.NEGATIVE);
    }

    @Test
    public void showDefaultSettingNotification() {
        TelemetryWrapper.showDefaultSettingNotification();
    }

    @Test
    public void clickDefaultSettingNotification() {
        TelemetryWrapper.clickDefaultSettingNotification();
    }

    @Test
    public void onDefaultBrowserServiceFailed() {
        TelemetryWrapper.onDefaultBrowserServiceFailed();
    }

    @Test
    public void promoteShareClickEventSetting() {
        TelemetryWrapper.promoteShareClickEvent(TelemetryWrapper.Value.DISMISS, TelemetryWrapper.Extra_Value.SETTING);
        TelemetryWrapper.promoteShareClickEvent(TelemetryWrapper.Value.SHARE, TelemetryWrapper.Extra_Value.SETTING);
    }

    @Test
    public void promoteShareClickEventSettingContextualHints() {
        TelemetryWrapper.promoteShareClickEvent(TelemetryWrapper.Value.DISMISS, TelemetryWrapper.Extra_Value.CONTEXTUAL_HINTS);
        TelemetryWrapper.promoteShareClickEvent(TelemetryWrapper.Value.SHARE, TelemetryWrapper.Extra_Value.CONTEXTUAL_HINTS);
    }

    @Test
    public void showPromoteShareDialog() {
        TelemetryWrapper.showPromoteShareDialog();
    }
}