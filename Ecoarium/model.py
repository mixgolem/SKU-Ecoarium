from tensorflow.keras.models import load_model
import cv2
import numpy as np
import sys

# 모델 불러오기
model = load_model('plastic_cup_classifier.h5')

# 이미지 파일 이름 받기
image_path = sys.argv[1]

def preprocess_image(image_path):
    img = cv2.imread(image_path)
    if img is None:
        print(f"Failed to load image from path: {image_path}")  # 이미지 로드 오류 출력
        return None
    
    img = cv2.resize(img, (128, 128))
    if img is None:
        print("Failed to resize image")  # 이미지 리사이즈 오류 출력
        return None
    
    img = img / 255.0
    img = np.expand_dims(img, axis=0)
    return img

def predict_image(image_path):
    processed_img = preprocess_image(image_path)
    if processed_img is None:
        print("Image processing failed")  # 이미지 처리 오류 출력
        return None
    
    prediction = model.predict(processed_img)
    return prediction

def model_decision(prediction):
    if prediction is None:
        return "Unable to process image"
    
    if prediction > 0.5:
        result = "ng"
    else:
        result = "clean"
    return result

prediction = predict_image(image_path)
#result = model_decision(prediction)

print("Prediction:", prediction)