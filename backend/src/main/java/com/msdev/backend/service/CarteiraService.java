package com.msdev.backend.service;

import com.msdev.backend.dto.request.CarteiraRequest;
import com.msdev.backend.dto.response.CarteiraResponse;
import com.msdev.backend.entity.CarteiraEntity;
import com.msdev.backend.entity.CategoriaEntity;
import com.msdev.backend.entity.UsuarioEntity;
import com.msdev.backend.exception.RecursoNaoEncontradoException;
import com.msdev.backend.repository.CarteiraRepository;
import com.msdev.backend.security.service.AuthenticationService;
import com.msdev.backend.utils.CarteiraMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListResourceBundle;

@Service
public class CarteiraService extends BaseServiceImpl<CarteiraEntity, Long, CarteiraRequest, CarteiraResponse> {

    private final CarteiraRepository carteiraRepository;
    private final CarteiraMapper carteiraMapper;
    private final AuthenticationService authenticationService;

    public CarteiraService(CarteiraRepository carteiraRepository, CarteiraMapper carteiraMapper, AuthenticationService authenticationService){
        super(carteiraRepository, carteiraMapper, "Carteira");
        this.carteiraRepository = carteiraRepository;
        this.carteiraMapper = carteiraMapper;
        this.authenticationService = authenticationService;
    }

    @Override
    protected List<CarteiraEntity> fetchAllEntities(){
        UsuarioEntity usuarioLogado = authenticationService.getLoggedIUser();
        return  carteiraRepository.findAllByUsuarioId(usuarioLogado.getId());
    }

}
