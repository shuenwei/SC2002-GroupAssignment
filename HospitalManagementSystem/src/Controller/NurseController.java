package Controller;

import Entity.Nurse;
import Entity.Patient;
import Entity.User;
import Repository.UserRepository;

public class NurseController {

    private Nurse nurse;

    public NurseController(Nurse nurse){
        this.nurse = nurse;
    }

    public void addPatient(Patient patient){
        UserRepository.add(patient);

    }

    public User getPatient(String hospitalId){
        return UserRepository.get(hospitalId);

    }

    public boolean removePatient(String hospitalId){

        if(UserRepository.get(hospitalId) instanceof Patient){
            UserRepository.remove(hospitalId);
            return true;
        }else{
            return false;
        }
        
    }

}
