package com.androiddreamer.unipoll.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.androiddreamer.unipoll.Application;
import com.androiddreamer.unipoll.model.Repository;
import com.androiddreamer.unipoll.util.SwiftyJSONObject;
import com.androiddreamer.unipoll.util.UDHelper;

public class ProfileFragmentViewModel extends ViewModel {
    Repository repository;
    UDHelper udHelper;

    public ProfileFragmentViewModel() {
        repository = Repository.getInstance();
        udHelper = new UDHelper(Application.applicationContext);
    }

    /**
     * Network calls
     */
    public LiveData<SwiftyJSONObject> callGetProfileData() {
//        String userId = udHelper.getString(UDHelper.KEY_USER_ID);
        String userId = "3170122";
        return repository.getUserDetails(userId);
    }
}
