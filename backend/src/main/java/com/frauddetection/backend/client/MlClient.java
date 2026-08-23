package com.frauddetection.backend.client;

import com.frauddetection.backend.dto.ml.MlPredictionRequest;
import com.frauddetection.backend.dto.ml.MlPredictionResponse;
import com.frauddetection.backend.exception.MlServiceException;

/**
 * Adapter contract for communicating with the Python ML microservice. The service layer must
 * depend on this interface, not MlClientImpl directly.
 */
public interface MlClient {

    /**
     * @throws MlServiceException if the ML service could not be reached, timed out, returned
     *                            an HTTP error, or returned a response that could not be understood
     */
    MlPredictionResponse predict(MlPredictionRequest request);
}
