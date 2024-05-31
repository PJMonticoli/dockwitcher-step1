package com.hotakakademy.dockwitcher.domain.factories

import com.hotakakademy.dockwitcher.DTO.ConductorDto
import com.hotakakademy.dockwitcher.domain.entities.Conductor
import com.hotakakademy.dockwitcher.domain.repositories.IConductorRepository
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Component

// Aca le tuve que agregar el @Component al factory para que funcione el Bean
@Component
class ConductorFactory(
    private val repository: IConductorRepository,
    private val modelMapper: ModelMapper
): IConductorFactory {
    override fun createFromDto(conductorDto: ConductorDto): Conductor {
        val conductor = Conductor()
        conductor.setRepository(repository)
        modelMapper.map(conductorDto, conductor)
        return conductor
    }
}
