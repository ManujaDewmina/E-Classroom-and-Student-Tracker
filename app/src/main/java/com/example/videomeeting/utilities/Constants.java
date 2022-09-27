package com.example.videomeeting.utilities;

import java.util.HashMap;

public class Constants {
    public static final String KEY_COLLECTION_STUDENTS = "students_collection";
    public static final String KEY_COLLECTION_TEACHERS = "teachers_collection";
    public static final String KEY_COLLECTION_NOTES = "notes_collection";

    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_ID_NUMBER = "id_number";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_FCM_TOKEN = "fcm_token";

    public static final String KEY_SUBJECT_CODE = "subject_code";
    public static final String KEY_NOTE_CODE = "note_code";
    public static final String KEY_NOTE_PASSWORD = "note_password";
    public static final String KEY_NOTE_NAME = "note_name";
    public static final String KEY_NOTE_URL = "note_url";


    public static final String KEY_PREFERENCE_NAME = "onlineClassroomPreference";
    public static final String KEY_IS_SIGNED_IN = "isSignedIn";

    public static final String REMOTE_MSG_AUTHORIZATION = "Authorization";
    public static final String REMOTE_MSG_CONTENT_TYPE = "Content-Type";

    public static HashMap<String,String> getRemoteMessageHeaders(){
        HashMap<String, String> headers = new HashMap<>();
        headers.put(
                Constants.REMOTE_MSG_AUTHORIZATION,
                "key=AAAAUqdtbyc:APA91bHbxtVc2Ro32GaZmJjvaRYP-qmpPRZzTtQ3NKpLUqanH6s9ncJHNxMqWnRq9VHVVUiLK4PzzRj_0vJuOnHyotpRDjduoRdHiiOQoJ1mM5oubMIlGTGZMtqXe_G0ClYFULH_BDLQ"
        );
        headers.put(Constants.REMOTE_MSG_CONTENT_TYPE, "application/jason");
        return headers;
    }
}
