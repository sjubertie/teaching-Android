# Android.mk


# Définition du dossier contenant les sources à partir de la macro my-dir
LOCAL_PATH := $(call my-dir)

# Nettoyage des variables LOCAL_... sauf LOCAL_PATH
include $(CLEAR_VARS)

# Inclusion des sous-dossiers
#include $(call all-subdir-makefiles)

# Bibliothèques à lier
#LOCAL_LDLIBS := -llog

# Nom du module, utilisé pour nommer le fichier lib$(LOCAL_MODULE).so
LOCAL_MODULE    := hello

# Liste des fichiers sources, ne doit pas contenir les headers
LOCAL_SRC_FILES := hello.cpp

# Génération à partir des infos définies précédemment
include $(BUILD_SHARED_LIBRARY)
